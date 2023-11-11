package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @see <a href="https://leetcode.com/problems/design-graph-with-shortest-path-calculator/submissions/1096453667/">Submission</a>
 */
public class LeetCode1786 {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1786.class);
    private static final long modulo = (long) (Math.pow(10, 9) + 7);

    public int countRestrictedPaths(int n, int[][] edgesArray) {
        List<List<Edge>> forward = IntStream.range(0, n).mapToObj(i -> (List<Edge>) new ArrayList<Edge>()).toList();
        List<List<Edge>> backward = IntStream.range(0, n).mapToObj(i -> (List<Edge>) new ArrayList<Edge>()).toList();
        for (int[] edgeArray : edgesArray) {
            Edge edge1 = new Edge(edgeArray[0] - 1, edgeArray[1] - 1, edgeArray[2]);
            forward.get(edge1.left()).add(edge1);
            backward.get(edge1.right()).add(edge1);
            Edge edge2 = new Edge(edgeArray[1] - 1, edgeArray[0] - 1, edgeArray[2]);
            forward.get(edge2.left()).add(edge2);
            backward.get(edge2.right()).add(edge2);
        }
        forward.forEach(e -> e.sort(Comparator.comparing(Edge::cost)));
        var distances = IntStream.range(0, forward.size()).map(i -> Integer.MAX_VALUE).toArray();
        var reachable = new BitSet(forward.size());
        calculateDistances(forward, distances);
        // simple remove for all edges that go up the slope
        forward.forEach(edges -> edges.removeIf(edge -> distances[edge.left()] <= distances[edge.right()]));
        backward.forEach(edges -> edges.removeIf(edge -> distances[edge.left()] <= distances[edge.right()]));
        // calculates reachability for each node, to sort out remaining up-the-slope edges
        forward.forEach(edges -> edges.sort(Comparator.comparing(ee -> distances[ee.right()])));
        calculateReachability(forward, reachable);
        // remove all edges that go to unreachable nodes
        forward.forEach(edges -> edges.removeIf(e -> !(reachable.get(e.left()) && reachable.get(e.right()))));
        backward.forEach(edges -> edges.removeIf(e -> !(reachable.get(e.left()) && reachable.get(e.right()))));
        return countRestrictedPathsRec(backward, distances, reachable);
    }

    public void calculateDistances(List<List<Edge>> edges, int[] distances) {
        var paths = new PriorityQueue<>(Comparator.comparing(Path::cost));
        paths.add(Path.of(edges.size(), edges.size() - 1));
        distances[edges.size() - 1] = 0;
        while (!paths.isEmpty()) {
            var path = paths.poll();
            List<Edge> next = edges.get(path.node());
            for (Edge edge : next) {
                if (!path.visited().get(edge.right())) {
                    var cost = path.cost() + edge.cost();
                    if (cost < distances[edge.right()]) {
                        distances[edge.right()] = cost;
                        var visited = new BitSet(edges.size());
                        visited.set(edge.right(), true);
                        visited.or(path.visited());
                        paths.add(new Path(edge.right(), cost, visited));
                    }
                }
            }
        }
    }

    public void calculateReachability(List<List<Edge>> forward, BitSet reachable) {
        var paths = new PriorityQueue<>(Comparator.comparing(Path::cost).reversed());
        paths.add(Path.of(forward.size(), 0));
        while (!paths.isEmpty()) {
            var path = paths.poll();
            List<Edge> next = forward.get(path.node());
            for (Edge edge : next) {
                if (edge.right() == (forward.size() - 1) || reachable.get(edge.right())) {
                    reachable.or(path.visited());
                    reachable.set(edge.right());
                    continue;
                }
                if (!path.visited().get(edge.right())) {
                    paths.add(new Path(edge.right(), path.cost() + 1, bitSetWithTrue(path.visited(), edge.right())));
                }
            }
        }
    }

    public int countRestrictedPathsRec(List<List<Edge>> backward, int[] distances, BitSet reachable) {
        long[] counts = LongStream.range(0, backward.size()).map(i -> -1).toArray();
        counts[0] = 1;
        IntStream.range(0, backward.size())
                .boxed()
                .filter(reachable::get)
                .sorted(Comparator.comparing((n -> distances[(int) n])).reversed())
                .forEach(n -> countRestrictedPathsRec(backward, counts, n));
        return (int) counts[backward.size() - 1];
    }

    public long countRestrictedPathsRec(List<List<Edge>> backward, long[] counts, int node) {
        if (counts[node] >= 0) {
            return counts[node];
        }
        long sum = 0;
        for (Edge edge : backward.get(node)) {
            sum += countRestrictedPathsRec(backward, counts, edge.left());
            if (sum >= modulo) {
                sum = sum % modulo;
            }
        }
        counts[node] = sum;
        return sum;
    }

    public static BitSet bitSetWithTrue(BitSet bs, int index) {
        BitSet updated = (BitSet) bs.clone();
        updated.set(index, true);
        return updated;
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
