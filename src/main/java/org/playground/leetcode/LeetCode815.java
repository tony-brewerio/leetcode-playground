package org.playground.leetcode;

import java.util.*;

/**
 * @see <a href="https://leetcode.com/problems/restore-the-array-from-adjacent-pairs/submissions/1096232787/">Submission</a>
 * <p>
 * Problem notes.
 * There are at most 500 buses overall. We can easily use BitSet or shorts perhaps?
 * As usual, Leetcode provies 1 base numbers, but I prefer 0 based instead.
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
 */
public class LeetCode815 {

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        // Setup.
        // Graph begins with same-bus edges, those cost 1, since we don't need to "hop" buses to get to them.
        // We still absolutely do need to assign them some cost to prevent algorithm from looping over.
        // Another way to avoid loops is to maintain a history of visited nodes but that is way more expensive.
        // Note that edge here is a bit special - it is unique by node and not by node + value
        // There is no point, after all, to hop a bus to get to a point that you can get to by riding the current line.
        Map<Stop, Set<Stop>> graph = new HashMap<>();
        Map<Integer, List<Integer>> busesPerStop = new HashMap<>();
        int busHopEdgeCost = 1;
        int maxStopNumber = -1;
        for (int bus = 0; bus < routes.length; bus++) {
            for (int stop : routes[bus]) {
                if (stop > maxStopNumber) {
                    maxStopNumber = stop;
                }
                var edges = graph.computeIfAbsent(new Stop(bus, stop - 1), x -> new HashSet<>());
                busesPerStop.computeIfAbsent(stop - 1, x -> new ArrayList<>()).add(bus);
                for (int next : routes[bus]) {
                    if (next != stop && edges.add(new Stop(bus, next - 1))) {
                        busHopEdgeCost++;
                    }
                }
            }
        }
        // To make sure we can actually calculate the amount of bus hops,
        // we must make sure that switching the bus costs more than riding any amount of bus stops on a bus.
        // If that condition is kept, we just divide the final cost, drop the remainder - and that's out hop count.
        // Now we add bus hopping edges to the graph, cost is variable depending on how many non-hop edges we have.
        for (var entry : busesPerStop.entrySet()) {
            for (int bus1 : entry.getValue()) {
                for (int bus2 : entry.getValue()) {
                    if (bus1 != bus2) {
                        graph.get(new Stop(bus1, entry.getKey())).add(new Stop(bus2, entry.getKey()));
                        graph.get(new Stop(bus2, entry.getKey())).add(new Stop(bus1, entry.getKey()));
                    }
                }
            }
        }
        var result = minHopsToDestination(graph, busesPerStop, maxStopNumber, busHopEdgeCost, source, target);
        return result;
    }

    private int minHopsToDestination(Map<Stop, Set<Stop>> graph,
                                     Map<Integer, List<Integer>> busesPerStop,
                                     int maxStopNumber,
                                     int busHopEdgeCost,
                                     int source,
                                     int target) {
        // we start our stack with one hop on every bus on the source stop
        var paths = new PriorityQueue<CalcDistancePath>();
        Map<Stop, Integer> distances = new HashMap<>();
        for (Integer bus : busesPerStop.get(source - 1)) {
            var stop = new Stop(bus, source - 1);
            paths.add(new CalcDistancePath(stop, busHopEdgeCost));
            distances.put(stop, busHopEdgeCost);
        }
        int bestEndStopDistance = Integer.MAX_VALUE;
        while (!paths.isEmpty()) {
            var prev = paths.poll();
            var prevStop = prev.stop();
            var prevStopBus = prev.stop().bus();
            var prevCost = prev.cost();
            if (prevCost > distances.getOrDefault(prevStop, Integer.MAX_VALUE)) {
                continue;
            }
            for (var nextStop : graph.get(prevStop)) {
                var nextStopBus = nextStop.bus();
                var nextStopStop = nextStop.stop();
                int nextStopCost = nextStopBus == prevStopBus ? 1 : busHopEdgeCost;
                var nextStopDistance = prevCost + nextStopCost;
                if (distances.getOrDefault(nextStop, Integer.MAX_VALUE) > nextStopDistance) {
                    distances.put(nextStop, nextStopDistance);
                    paths.add(new CalcDistancePath(nextStop, nextStopDistance));
                    if (nextStopStop == (target - 1) && nextStopDistance < bestEndStopDistance) {
                        bestEndStopDistance = nextStopDistance;
                    }
                }
            }
        }
        return bestEndStopDistance != Integer.MAX_VALUE ? bestEndStopDistance / busHopEdgeCost : -1;
    }

    public record CalcDistancePath(Stop stop, int cost) implements Comparable<CalcDistancePath> {
        @Override
        public int compareTo(CalcDistancePath o) {
            return cost - o.cost();
        }
    }

    public record Stop(int bus, int stop) {
    }
}
