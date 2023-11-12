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
    private AveragingOperationTimer timer;
    private static final long MODULO = (long) (Math.pow(10, 9) + 7);

    public int countRestrictedPaths(int n, int[][] edgesArray) {
        timer = new AveragingOperationTimer(log, "setup - {} micros");
        List<List<Edge>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>(4));
        }
        for (int[] edge : edgesArray) {
            graph.get(edge[0] - 1).add(new Edge(edge[1] - 1, edge[2]));
            graph.get(edge[1] - 1).add(new Edge(edge[0] - 1, edge[2]));
        }
        timer.close();
        //
        timer = new AveragingOperationTimer(log, "distances - {} micros");
        var distances = calculateDistances(graph);
        timer.close();
        timer = new AveragingOperationTimer(log, "counts - {} micros");
        var count = countRestrictedPathsDpRec(graph, distances);
        timer.close();
        return count;
    }

    public int[] calculateDistances(List<List<Edge>> graph) {
        var paths = new PriorityQueue<CalcDistancePath>();
        paths.add(new CalcDistancePath(graph.size() - 1, 0));
        var distances = new int[graph.size()];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[graph.size() - 1] = 0;
        while (!paths.isEmpty()) {
            var path = paths.poll();
            var pathNode = path.node();
            var pathCost = path.cost();
            if (distances[pathNode] != pathCost) {
                continue;
            }
            for (var edge : graph.get(pathNode)) {
                int edgeNext = edge.next();
                int edgeCost = edge.cost();
                var edgeDistance = distances[pathNode] + edgeCost;
                if (distances[edgeNext] > edgeDistance) {
                    distances[edgeNext] = edgeDistance;
                    paths.add(new CalcDistancePath(edgeNext, edgeCost + pathCost));
                }
            }
        }
        return distances;
    }

    private int countRestrictedPathsDpRec(List<List<Edge>> graph, int[] distances) {
        var counts = new long[graph.size()];
        Arrays.fill(counts, -1);
        counts[graph.size() - 1] = 1;
        return (int) countRestrictedPathsDpRec(graph, distances, counts, 0);
    }

    private long countRestrictedPathsDpRec(List<List<Edge>> graph, int[] distances, long[] counts, int node) {
        long count = counts[node];
        if (count != -1) {
            return count;
        }
        count = 0;
        for (Edge edge : graph.get(node)) {
            int edgeNext = edge.next();
            if (distances[node] > distances[edgeNext]) {
                count += countRestrictedPathsDpRec(graph, distances, counts, edgeNext);
                count = count % MODULO;
            }
        }
        counts[node] = count;
        return count;
    }

    public record Edge(int next, int cost) {
    }

    public record CalcDistancePath(int node, int cost) implements Comparable<CalcDistancePath> {
        @Override
        public int compareTo(CalcDistancePath o) {
            return cost - o.cost();
        }
    }
}
