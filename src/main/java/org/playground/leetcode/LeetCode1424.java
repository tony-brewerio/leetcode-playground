package org.playground.leetcode;

import org.playground.leetcode.helpers.AveragingOperationTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @see <a href="https://leetcode.com/problems/diagonal-traverse-ii/submissions/1104512066/">Submission</a>
 * Runtime: 27 ms, Beats 75.00% of users with Java
 * Memory: 75.16 MB, Beats 35.95% of users with Java
 * <p>
 * Much better runtime, worse memory.
 * Still kinda not that fast though?
 */
public class LeetCode1424 {

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        Map<Integer, int[]> diagonals = new HashMap<>();
        int total = 0;
        for (int y = nums.size() - 1; y >= 0; y--) {
            var row = nums.get(y);
            for (int x = 0; x < row.size(); x++) {
                int sum = x + y;
                int val = row.get(x);
                var diag = diagonals.get(sum);
                if (diag != null) {
                    if (diag[0] == diag.length) {
                        diag = Arrays.copyOf(diag, (int) (diag.length * 1.4 + 8));
                        diagonals.put(sum, diag);
                    }
                    diag[diag[0]++] = val;
                    total++;
                } else {
                    diag = new int[8];
                    diag[0] = 1;
                    diag[diag[0]++] = val;
                    total++;
                    diagonals.put(sum, diag);
                }
            }
        }
        var sums = diagonals.keySet().toArray(Integer[]::new);
        Arrays.sort(sums);
        var accum = new int[total];
        var ai = 0;
        for (Integer sum : sums) {
            var diag = diagonals.get(sum);
            for (int i = 1; i < diag[0]; i++) {
                accum[ai++] = diag[i];
            }
        }
        return accum;
    }
}
