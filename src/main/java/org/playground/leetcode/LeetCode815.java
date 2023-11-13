package org.playground.leetcode;

import java.util.Arrays;
import java.util.BitSet;
import java.util.PriorityQueue;

/**
 * @see <a href="https://leetcode.com/problems/bus-routes/submissions/1097536372/">Submission</a>
 * Runtime: 95 ms, Beats 57.06% of users with Java
 * Memory: 58.39 MB, Beats 94.26% of users with Java
 * <p>
 * Problem notes.
 * There are at most 500 buses overall. We can easily use BitSet or shorts perhaps?
 * As usual, Leetcode provies 1 base numbers, which sometimes kind of sucks.
 * Bus stops per route are always sorted, or at least we can sort them to save up some time later?
 * <p>
 * Constraints:
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 10^5
 * All the values of routes[i] are unique.
 * sum(routes[i].length) <= 10^5
 * 0 <= routes[i][j] < 10^6
 * 0 <= source, target < 10^6
 * <p>
 * Attemped this task from the wrong angle at first, trying to treat both riding the bus and switching buses as edges.
 * This does not work, since some bus routes are very very long, so you always fail execution time constraint.
 * After a couple of pretty dumb attempts to make bazillion edges work, I've realized that there is no point.
 * Since we only really need to return a number of bus hops, we can instead build a graph where edges are bus transfers.
 * And, we convert source and target stop to arrays of buses, picking all buses that stop at source or target.
 * Thus the problem is reduced to searching shortest path in a graph of bus transfers.
 * There are not that many, comparatively speaking, transfers, so actual distance ranking is plenty fast.
 * <p>
 * Instead, you get the problem of how to compile the graph efficiently.
 * I have decided to just iterate over all possible stop numbers, and at each step find all buses that stop there.
 * Then, those buses are combined, each bus getting connections to all other buses.
 * BitSet makes this very easy and decently fast.
 * There are some unsorted routes in LeetCode test cases, so I have to presort them.
 * <p>
 * Notes.
 * Bus stops are treated as starting from zero, as they are based off of routes index.
 * Stops are 1 based, since I just treated them as is.
 * BitSets are very memory efficient, which is not surprising.
 */
public class LeetCode815 {

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        for (int[] route : routes) {
            Arrays.sort(route);
        }
        var sources = new BitSet();
        var targets = new BitSet();
        for (int i = 0; i < routes.length; i++) {
            var route = routes[i];
            if (Arrays.binarySearch(route, source) >= 0) {
                sources.set(i);
            }
            if (Arrays.binarySearch(route, target) >= 0) {
                targets.set(i);
            }
        }
        // this relies on the fact that input were all sorted
        // the idea is that we iterate over all the possible stop numbers
        // then scan routes from the start, looking for the number
        // all buses where stop was found are collected together
        var edges = new BitSet[routes.length];
        {
            for (int i = 0; i < routes.length; i++) {
                edges[i] = new BitSet(routes.length);
            }
            int[] offsets = new int[routes.length];
            for (int stop = 0; stop < 1_000_000; stop++) {
                var buses = new BitSet(routes.length);
                boolean end = true;
                for (int bus = 0; bus < offsets.length; bus++) {
                    int[] route = routes[bus];
                    while (offsets[bus] < route.length && route[offsets[bus]] < stop) {
                        offsets[bus]++;
                    }
                    if (offsets[bus] == route.length) {
                        continue;
                    } else {
                        end = false;
                    }
                    if (route[offsets[bus]] == stop) {
                        buses.set(bus);
                    }
                }
                if (end) {
                    break;
                }
                for (int i = buses.nextSetBit(0); i != -1; i = buses.nextSetBit(i + 1)) {
                    edges[i].or(buses);
                }
            }
        }
        return minHopsToDestination(edges, sources, targets);
    }

    private int minHopsToDestination(BitSet[] edges, BitSet sources, BitSet targets) {
        // we start our stack with one hop on every bus on the source stop
        int[] distances = new int[edges.length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        var stack = new PriorityQueue<CalcDistancePath>();
        sources.stream().forEach(bus -> {
            stack.add(new CalcDistancePath(bus, 1));
            distances[bus] = 1;
        });
        while (!stack.isEmpty()) {
            var prev = stack.poll();
            var prevBus = prev.bus();
            var prevCost = prev.cost();
            if (prevCost > distances[prevBus]) {
                continue;
            }
            var nextBusSet = edges[prevBus];
            for (int nextBus = nextBusSet.nextSetBit(0); nextBus != -1; nextBus = nextBusSet.nextSetBit(nextBus + 1)) {
                if (nextBus == prevBus) {
                    continue;
                }
                var nextCost = prevCost + 1;
                if (nextCost < distances[nextBus]) {
                    distances[nextBus] = nextCost;
                    stack.add(new CalcDistancePath(nextBus, nextCost));
                }
            }
        }
        return targets.stream()
                .map(bus -> distances[bus])
                .filter(d -> d != Integer.MAX_VALUE)
                .min()
                .orElse(-1);
    }

    public record CalcDistancePath(int bus, int cost) implements Comparable<CalcDistancePath> {
        @Override
        public int compareTo(CalcDistancePath o) {
            return cost - o.cost;
        }
    }

}
