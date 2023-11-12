package org.playground.leetcode;

import org.playground.leetcode.helpers.AveragingOperationTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @see <a href="https://leetcode.com/problems/design-graph-with-shortest-path-calculator/submissions/1096453667/">Submission</a>
 */
public class LeetCode1786 {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1786.class);
    private static final long MODULO = (long) (Math.pow(10, 9) + 7);

    private void addEdgeKeepingOrder(List<Edge> edges, Edge edge) {
        for (int i = 0; i < edges.size(); i++) {
            if (edge.cost() < edges.get(i).cost()) {
                edges.add(i, edge);
                return;
            }
        }
        edges.add(edge);
    }

    public int countRestrictedPaths(int n, int[][] edgesArray) {
        List<List<Edge>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>(4));
        }
        for (int[] edge : edgesArray) {
            addEdgeKeepingOrder(graph.get(edge[0] - 1), new Edge(edge[1] - 1, edge[2]));
            addEdgeKeepingOrder(graph.get(edge[1] - 1), new Edge(edge[0] - 1, edge[2]));
        }
        //
        var distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        calculateDistances(graph, distances);
        var timer = new AveragingOperationTimer(log, "calculate count - {} micros");
        var count = countRestrictedPathsDpRec(graph, distances);
        timer.close();
        return count;
    }

    public BitSet calculateDistances(List<List<Edge>> graph, int[] distances) {
        var paths = new PriorityQueue<CalcDistancePath>();
        paths.add(new CalcDistancePath(graph.size() - 1, 0));
        distances[graph.size() - 1] = 0;
        while (!paths.isEmpty()) {
            var path = paths.poll();
            for (var edge : graph.get(path.node())) {
                int edgeNext = edge.next();
                int edgeCost = edge.cost();
                var distance = distances[path.node()] + edgeCost;
                if (distances[edgeNext] > distance) {
                    distances[edgeNext] = distance;
                    paths.add(new CalcDistancePath(edgeNext, edgeCost));
                }
            }
        }
        return null;
    }

    private int countRestrictedPathsDpRec(List<List<Edge>> graph, int[] distances) {
        var counts = new long[graph.size()];
        Arrays.fill(counts, -1);
        counts[graph.size() - 1] = 1;
        return (int) countRestrictedPathsDpRec(graph, distances, counts, 0);
    }

    private long countRestrictedPathsDpRec(List<List<Edge>> graph, int[] distances, long[] counts, int node) {
        if (counts[node] != -1) {
            return counts[node];
        }
        long count = 0;
        for (Edge edge : graph.get(node)) {
            if (distances[edge.next()] < distances[node]) {
                count += countRestrictedPathsDpRec(graph, distances, counts, edge.next());
                if (count >= MODULO) {
                    count = count % MODULO;
                }
            }
        }
        counts[node] = count;
        return count;
    }

//    private boolean addNextCountTask(List<List<Edge>> graph, int[] distances, ArrayDeque<CounterTask> stack, int node, int ei, long count) {
//        var edges = graph.get(node);
//        for (int i = ei; i < edges.size(); i++) {
//            var edge = edges.get(i);
//            var edgeNext = edge.next();
//            if (distances[edgeNext] < distances[node]) {
//                stack.addFirst(new CounterTask(node, edgeNext, i, count));
//                return true;
//            }
//        }
//        return false;
//    }

//    private int countRestrictedPathsDpUnrolled(List<List<Edge>> graph, int[] distances) {
//        var counts = new long[graph.size()];
//        Arrays.fill(counts, -1);
//        counts[graph.size() - 1] = 1;
//        var stack = new ArrayDeque<CounterTask>();
//        if (!addNextCountTask(graph, distances, stack, 0, 0, 0)) {
//            throw new RuntimeException("failed to add initial dp task");
//        }
//        while (!stack.isEmpty()) {
//            var task = stack.poll();
//            long count = counts[task.right()];
//            if (count != -1) {
//                long nextCount = task.count() + count;
//                if (nextCount >= MODULO) {
//                    nextCount = nextCount % MODULO;
//                }
//                if (!addNextCountTask(graph, distances, stack, task.left(), task.ei() + 1, nextCount)) {
//                    counts[task.left()] = nextCount;
//                }
//            } else {
//                stack.addFirst(task);
//                addNextCountTask(graph, distances, stack, task.right(), 0, 0);
//            }
//        }
//        return (int) counts[0];
//    }
//
//    public record CounterTask(int left, int right, int ei, long count) {
//    }

    public record Edge(int next, int cost) {
    }

    public record CalcDistancePath(int node, int cost) implements Comparable<CalcDistancePath> {
        @Override
        public int compareTo(CalcDistancePath o) {
            return cost - o.cost();
        }
    }
}
