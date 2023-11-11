package org.playground.leetcode;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @see <a href="https://leetcode.com/problems/design-graph-with-shortest-path-calculator/submissions/1096453667/">Submission</a>
 */
public class LeetCode2642 {
    public static class Graph {
        private final int n;
        private final List<List<Edge>> edges;

        public Graph(int n, int[][] edges) {
            this.n = n;
            this.edges = IntStream.range(0, n).mapToObj(i -> (List<Edge>) new ArrayList<Edge>()).toList();
            for (int[] edge : edges) {
                addEdge(edge);
            }
        }

        public void addEdge(int[] edge) {
            edges.get(edge[0]).add(new Edge(edge[0], edge[1], edge[2]));
        }

        public int shortestPath(int node1, int node2) {
            var paths = new PriorityQueue<>(Comparator.comparing(Path::cost));
            paths.add(Path.of(n, node1));
            var bestCostByNode = IntStream.range(0, n).map(i -> Integer.MAX_VALUE).toArray();
            bestCostByNode[node1] = 0;
            while (!paths.isEmpty()) {
                var path = paths.poll();
                if (path.node() == node2) {
                    return path.cost();
                }
                List<Edge> next = edges.get(path.node());
                for (Edge edge : next) {
                    if (!path.visited().get(edge.right())) {
                        var cost = path.cost() + edge.cost();
                        if (cost < bestCostByNode[edge.right()]) {
                            bestCostByNode[edge.right()] = cost;
                            var visited = new BitSet(n);
                            visited.set(edge.right(), true);
                            visited.or(path.visited());
                            paths.add(new Path(edge.right(), cost, visited));
                        }
                    }
                }
            }
            return -1;
        }

        public record Edge(int left, int right, int cost) {
        }

        public record Path(int node, int cost, BitSet visited) {
            public static Path of(int n, int node) {
                var visited = new BitSet(n);
                visited.set(node, true);
                return new Path(node, 0, visited);
            }
        }
    }
}
