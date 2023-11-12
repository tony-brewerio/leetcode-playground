package org.playground.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @see <a href="https://leetcode.com/problems/number-of-restricted-paths-from-first-to-last-node/submissions/1097267570/">Submission</a>
 * Runtime: 44 ms, Beats 97.73% of users with Java
 * Memory: 76.65 MB, Beats 27.27% of users with Java
 * <p>
 * I have spent way too much time with this.
 * The vast majority of it lost simply because I have decided to sort edges in node arrays.
 * Otherwise, the end solution is overingeneered, and it runs very-very fast.
 * Trades some memory for speed by allocating one big array, though there was no reason at all to go that far.
 * <p>
 * Leetcode has very high stack size, way up from default 1 MB, and it seems intentional for what it is.
 * Dynamic programming part will fail with StackOverflowError without -Xss8m or somewhere around that.
 * Recursion there can be pretty easily unrolled with ArrayDeque, but that's a bit slower, and I decided not to care.
 * <p>
 * Notes.
 * PriorityQueue works faster with Comparable POJOs than with Comparator sorted small arrays.
 * Any data Leetcode provides, iterate in the order they gave it originally.
 */
public class LeetCode1786 {
    private static final long MODULO = (long) (Math.pow(10, 9) + 7);

    public static class SuperGraphIntArray {
        private static final int CHUNK_EDGES = 4;
        private static final int CHUNK_BYTES = 1 + (CHUNK_EDGES * 2);
        private static final int SEPARATE_ARRAY_CAPACITY = 4 + CHUNK_EDGES * 2 * 2;
        private static final double SEPARATE_ARRAY_FACTOR = 1.5;
        public final int n;
        public final int[] nodesInSingleArray;
        public final int[][] nodesInSeparateArrays;

        public SuperGraphIntArray(int n, int[][] edges) {
            // okay, so here's an idea, though it is probably stupid as hell
            // I will attempt to put the entire graph into one large linear array, where each node is given a section
            // for a full 20000 nodes times 5 edges graph it will be less than a single megabyte
            // this will probably make things worse by screwing up cpu cache, but why not
            this.n = n;
            this.nodesInSingleArray = new int[n * CHUNK_BYTES];
            this.nodesInSeparateArrays = new int[n][];
            for (int[] edge : edges) {
                addEdgeToNode(edge[0] - 1, edge[1] - 1, edge[2]);
                addEdgeToNode(edge[1] - 1, edge[0] - 1, edge[2]);
            }
        }

        public void addEdgeToNode(int node, int next, int cost) {
            int lower = node * CHUNK_BYTES;
            int count = nodesInSingleArray[lower];
            nodesInSingleArray[lower]++;
            if (count < CHUNK_EDGES) {
                int offset = lower + 1 + count * 2;
                nodesInSingleArray[offset] = next;
                nodesInSingleArray[offset + 1] = cost;
            } else {
                var array = nodesInSeparateArrays[node];
                if (array == null) {
                    array = nodesInSeparateArrays[node] = new int[SEPARATE_ARRAY_CAPACITY];
                }
                int edgeNextIndex = (count - CHUNK_EDGES) * 2;
                int edgeCostIndex = (count - CHUNK_EDGES) * 2 + 1;
                if (edgeCostIndex >= array.length) {
                    array = nodesInSeparateArrays[node] = Arrays.copyOf(array, (int) (array.length * SEPARATE_ARRAY_FACTOR));
                }
                array[edgeNextIndex] = next;
                array[edgeCostIndex] = cost;
            }
        }

        public int edgeCountByNode(int node) {
            int lower = node * CHUNK_BYTES;
            return nodesInSingleArray[lower];
        }

        public int edgeNextByNode(int node, int edge) {
            // upper edges are stored in separate array, but lower ones still in the main one
            if (edge < CHUNK_EDGES) {
                int lower = node * CHUNK_BYTES;
                return nodesInSingleArray[lower + 1 + edge * 2];
            } else {
                return nodesInSeparateArrays[node][(edge - CHUNK_EDGES) * 2];
            }
        }

        public int edgeCostByNode(int node, int edge) {
            // upper edges are stored in separate array, but lower ones still in the main one
            if (edge < CHUNK_EDGES) {
                int lower = node * CHUNK_BYTES;
                return nodesInSingleArray[lower + 1 + edge * 2 + 1];
            } else {
                return nodesInSeparateArrays[node][(edge - CHUNK_EDGES) * 2 + 1];
            }
        }
    }

    public int countRestrictedPaths(int n, int[][] edgesArray) {
        var sg = new SuperGraphIntArray(n, edgesArray);
        var distances = calculateDistances(sg);
        return countRestrictedPathsDpRec(sg, distances);
    }

    private int[] calculateDistances(SuperGraphIntArray sg) {
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
            int edgeCount = sg.edgeCountByNode(pathNode);
            for (var edge = 0; edge < edgeCount; edge++) {
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

    private int countRestrictedPathsDpRec(SuperGraphIntArray sg, int[] distances) {
        var counts = new long[sg.n];
        Arrays.fill(counts, -1);
        counts[sg.n - 1] = 1;
        return (int) countRestrictedPathsDpRec(sg, distances, counts, 0);
    }

    private long countRestrictedPathsDpRec(SuperGraphIntArray sg, int[] distances, long[] counts, int node) {
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
