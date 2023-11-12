package org.playground.leetcode;

import org.playground.leetcode.helpers.AveragingOperationTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @see <a href="https://leetcode.com/problems/restore-the-array-from-adjacent-pairs/submissions/1096232787/">Submission</a>
 * <p>
 * Problem notes.
 * There are at most 500 buses overall. We can easily use BitSet or shorts perhaps?
 * As usual, Leetcode provies 1 base numbers, which sometimes kind of sucks.
 * <p>
 * Constraints:
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 10^5
 * All the values of routes[i] are unique.
 * sum(routes[i].length) <= 10^5
 * 0 <= routes[i][j] < 10^6
 * 0 <= source, target < 10^6
 * <p>
 * Notes.
 * Doing -1 all the time is very awkward. Unless I use flat array arithmetic for something, it is kind of pointless.
 * Bus stops are treated as starting from zero, as they are based off of routes index.
 * Stops are 1 based, since I just treated them as is.
 */
public class LeetCode815 {
    private static final Logger log = LoggerFactory.getLogger(LeetCode815.class);
    private AveragingOperationTimer timer;

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        // By definition, there are less than 10^6 individual bus stops and at most 500 routes.
        // Thus, we can represent each unique stop at each route by a single number under 50_000_000.
        // Basically, stop id will be bus_n * 500 + stop_n.
        // We can make an array of ints of this size, but it will cost like 400 MB, which is probably too much.
        // Still, regular int should be a tad bit faster overall than POJO.
        log.info("=== LeetCode 815 ===");
        timer = new AveragingOperationTimer(log, "setup - {} ms");
        int maxStopNumber = -1;
        for (int[] route : routes) {
            for (int stop : route) {
                if (stop > maxStopNumber) {
                    maxStopNumber = stop;
                }
            }
        }
        BitSet[] transfers = new BitSet[maxStopNumber + 1];
        for (int bus = 0; bus < routes.length; bus++) {
            int[] route = routes[bus];
            for (int stop : route) {
                var tr = transfers[stop];
                if (tr == null) {
                    tr = transfers[stop] = new BitSet(routes.length);
                }
                tr.set(bus);
            }
        }
        timer.close();
        timer = new AveragingOperationTimer(log, "distances - {} ms");
        var result = minHopsToDestination2(routes, transfers, ((long) maxStopNumber) * routes.length, source, target);
        timer.close();
        return result;
    }

    private int minHopsToDestination2(int[][] routes,
                                      BitSet[] transfers,
                                      long busHopEdgeCost,
                                      int source,
                                      int target) {
        // we start our stack with one hop on every bus on the source stop
        Map<Stop, Long> distances = new HashMap<>();
        var stack = new PriorityQueue<CalcDistancePath>();
        transfers[source].stream().forEach(bus -> {
            var stop = new Stop(bus, source);
            stack.add(new CalcDistancePath(stop, busHopEdgeCost));
            distances.put(stop, busHopEdgeCost);
        });
        long bestEndStopDistance = Long.MAX_VALUE;
        while (!stack.isEmpty()) {
            var prev = stack.poll();
            var prevStop = prev.stop();
            var prevStopStop = prev.stop().stop();
            var prevStopBus = prev.stop().bus();
            var prevCost = prev.cost();
            var prevStopBestDistanceSoFar = distances.getOrDefault(prevStop, Long.MAX_VALUE);
            if (prevCost > bestEndStopDistance || prevCost > prevStopBestDistanceSoFar) {
                continue;
            }
            // start with following the same bus route that we are on, with cost = 1
            for (int nextStopStop : routes[prevStopBus]) {
                if (nextStopStop != prevStopStop) {
                    var nextStop = new Stop(prevStopBus, nextStopStop);
                    var nextStopCost = prevCost + 1;
                    if (distances.getOrDefault(nextStop, Long.MAX_VALUE) > nextStopCost) {
                        distances.put(nextStop, nextStopCost);
                        stack.add(new CalcDistancePath(nextStop, nextStopCost));
                        if (nextStopStop == target && nextStopCost < bestEndStopDistance) {
                            bestEndStopDistance = nextStopCost;
                        }
                    }
                }
            }
            // then follow all bus hops from the current stop, with cost = busHopEdgeCost
            var tr = transfers[prevStopStop];
            for (int nextStopBus = tr.nextSetBit(0); nextStopBus != -1; nextStopBus = tr.nextSetBit(nextStopBus + 1)) {
                if (nextStopBus != prevStopBus) {
                    var nextStop = new Stop(nextStopBus, prevStopStop);
                    var nextStopCost = prevCost + busHopEdgeCost;
                    if (distances.getOrDefault(nextStop, Long.MAX_VALUE) > nextStopCost) {
                        distances.put(nextStop, nextStopCost);
                        stack.add(new CalcDistancePath(nextStop, nextStopCost));
                        if (prevStopBus == target && nextStopCost < bestEndStopDistance) {
                            bestEndStopDistance = nextStopCost;
                        }
                    }
                }
            }
        }
        return (int) (bestEndStopDistance != Long.MAX_VALUE ? bestEndStopDistance / busHopEdgeCost : -1);
    }

    public record CalcDistancePath(Stop stop, long cost) implements Comparable<CalcDistancePath> {
        @Override
        public int compareTo(CalcDistancePath o) {
            return Long.compare(cost, o.cost);
        }
    }

    public record Stop(int bus, int stop) {
    }
}
