package org.playground.leetcode;

import org.playground.leetcode.helpers.AveragingOperationTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @see <a href="https://leetcode.com/problems/design-graph-with-shortest-path-calculator/submissions/1096453667/">Submission</a>
 */
public class LeetCode1786 {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1786.class);
    private AveragingOperationTimer timer;
    private static final long MODULO = (long) (Math.pow(10, 9) + 7);

    private static class SuperGraph {
        private static final int CHUNK = 1 + (5 * 2);
        public final int n;
        public final int[] edges;

        public SuperGraph(int n, int[][] edges) {
            // okay, so here's an idea, though it is probably stupid as hell
            // I will attempt to put the entire graph into one large linear array, where each node is given a section
            // for a full 20000 nodes times 5 edges graph it will be less than a single megabyte
            // this will probably make things worse by screwing up cpu cache, but why not
            this.n = n;
            this.edges = new int[n * CHUNK];
            for (int[] edge : edges) {
                addEdgeToNode(edge[0] - 1, edge[1] - 1, edge[2]);
                addEdgeToNode(edge[1] - 1, edge[0] - 1, edge[2]);
            }
        }

        public void addEdgeToNode(int node, int next, int cost) {
            int lower = node * CHUNK;
            int count = edges[lower];
            int offset = lower + 1 + count * 2;
            edges[lower]++;
            try {
                edges[offset] = next;
                edges[offset + 1] = cost;
            } catch (ArrayIndexOutOfBoundsException exception) {
                log.info("node {} failed to add an edge, has {} edges", node, count + 1);
            }
        }

        public int edgeCountByNode(int node) {
            int lower = node * CHUNK;
            return edges[lower];
        }

        public int edgeNextByNode(int node, int edge) {
            int lower = node * CHUNK;
            return edges[lower + 1 + edge * 2];
        }

        public int edgeCostByNode(int node, int edge) {
            int lower = node * CHUNK;
            return edges[lower + 1 + edge * 2 + 1];
        }
    }


    public int countRestrictedPaths(int n, int[][] edgesArray) {
        timer = new AveragingOperationTimer(log, "sg setup - {} micros");
        var sg = new SuperGraph(n, edgesArray);
        timer.close();
        timer = new AveragingOperationTimer(log, "setup - {} micros");
        timer = new AveragingOperationTimer(log, "distances - {} micros");
        var distances = calculateDistances(sg);
        timer.close();
        timer = new AveragingOperationTimer(log, "counts - {} micros");
        var count = countRestrictedPathsDpRec(sg, distances);
        timer.close();
        return count;
    }

    private int[] calculateDistances(SuperGraph sg) {
        var paths = new PriorityQueue<CalcDistancePath>();
        paths.add(new CalcDistancePath(sg.n - 1, 0));
        var distances = new int[sg.n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[sg.n - 1] = 0;
        while (!paths.isEmpty()) {
            var path = paths.poll();
            var pathNode = path.node();
            var pathCost = path.cost();
            if (pathCost > distances[pathNode]) {
                continue;
            }
            for (var edge = 0; edge < sg.edgeCountByNode(pathNode); edge++) {
                int edgeNext = sg.edgeNextByNode(pathNode, edge);
                int edgeCost = sg.edgeCostByNode(pathNode, edge);
                var edgeDistance = pathCost + edgeCost;
                if (distances[edgeNext] > edgeDistance) {
                    distances[edgeNext] = edgeDistance;
                    paths.add(new CalcDistancePath(edgeNext, edgeDistance));
                }
            }
        }
        return distances;
    }

    private int countRestrictedPathsDpRec(SuperGraph sg, int[] distances) {
        var counts = new long[sg.n];
        Arrays.fill(counts, -1);
        counts[sg.n - 1] = 1;
        return (int) countRestrictedPathsDpRec(sg, distances, counts, 0);
    }

    private long countRestrictedPathsDpRec(SuperGraph sg, int[] distances, long[] counts, int node) {
        long count = counts[node];
        if (count != -1) {
            return count;
        }
        count = 0;
        for (var edge = 0; edge < sg.edgeCountByNode(node); edge++) {
            var edgeNext = sg.edgeNextByNode(node, edge);
            if (distances[node] > distances[edgeNext]) {
                count += countRestrictedPathsDpRec(sg, distances, counts, edgeNext);
                count = count % MODULO;
            }
        }
        counts[node] = count;
        return count;
    }

    public record CalcDistancePath(int node, int cost) implements Comparable<CalcDistancePath> {
        @Override
        public int compareTo(CalcDistancePath o) {
            return cost - o.cost();
        }
    }
}
