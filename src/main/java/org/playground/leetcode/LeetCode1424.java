package org.playground.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/diagonal-traverse-ii/submissions/1104519889/">Submission</a>
 * Runtime: 21 ms, Beats 87.29% of users with Java
 * Memory: 61.82 MB, Beats 93.80% of users with Java
 * <p>
 * Very fast and memory efficient.
 * Storing tuples in an array and sorting them with a lambda and not Comparator is a bit faster.
 */
public class LeetCode1424 {

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int total = 0;
        for (var row : nums) {
            total += row.size();
        }
        var tuples = new int[total][];
        var ti = 0;
        for (int y = 0; y < nums.size(); y++) {
            var row = nums.get(y);
            for (int x = 0; x < row.size(); x++) {
                tuples[ti++] = new int[]{x + y, y, row.get(x)};
            }
        }
        Arrays.sort(tuples, (t1, t2) -> {
            if (t1[0] == t2[0]) {
                return t2[1] - t1[1];
            } else {
                return t1[0] - t2[0];
            }
        });
        var accum = new int[ti];
        for (int i = 0; i < ti; i++) {
            accum[i] = tuples[i][2];
        }
        return accum;
    }
}
