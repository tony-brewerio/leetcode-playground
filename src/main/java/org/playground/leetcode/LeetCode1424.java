package org.playground.leetcode;

import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/diagonal-traverse-ii/submissions/1104475202/">Submission</a>
 * Runtime: 2593 ms, Beats 5.06% of users with Java
 * Memory: 66.26 MB, Beats 76.65% of users with Java
 * <p>
 * Not entirely sure how to optimize this, even with hints.
 * The solution is very straightforward and brute force-y.
 * Barely squeaks by with terrible runtime score, had to convert input to arrays to avoid timeouts.
 */
public class LeetCode1424 {

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int[][] arr = new int[nums.size()][];
        for (int i = 0; i < nums.size(); i++) {
            arr[i] = listOfIntegersToIntArray(nums.get(i));
        }
        int maxlen = 0;
        int total = 0;
        for (int[] row : arr) {
            int rs = row.length;
            if (rs > maxlen) {
                maxlen = rs;
            }
            total += rs;
        }
        int[] accum = new int[total];
        int ai = 0;
        for (int r = 0; r < arr.length; r++) {
            for (int x = 0, y = r; x < maxlen && y >= 0; x++, y--) {
                var row = arr[y];
                if (x < row.length) {
                    accum[ai++] = row[x];
                }
            }
        }
        for (int c = 1; c <= maxlen; c++) {
            for (int x = c, y = arr.length - 1; x < maxlen && y >= 0; x++, y--) {
                var row = arr[y];
                if (x < row.length) {
                    accum[ai++] = row[x];
                }
            }
        }
        return accum;
    }

    private static int[] listOfIntegersToIntArray(List<Integer> list) {
        int[] array = new int[list.size()];
        int i = 0;
        for (int num : list) {
            array[i++] = num;
        }
        return array;
    }
}
