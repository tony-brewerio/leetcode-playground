package org.playground.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/diagonal-traverse-ii/submissions/1104531523/">Submission</a>
 * Runtime: 11 ms, Beats 100.00% of users with Java
 * Memory: 63.24 MB, Beats 88.12% of users with Java
 * <p>
 * Seems obvious in retrospect.
 * This is identical to a previous HashMap solution, which was sorta fast but not that fast, and was not very memory efficient.
 * However, we can also use a flat array to store all diagonals, since max diagonal sum of x+y is not that large.
 * The resulting solution operates on int arrays as much as possible.
 * Also, switched the row iteration order to reverse one. This way diagonals get naturally ordered as we want.
 */
public class LeetCode1424 {

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int total = 0;
        int maxrs = 0;
        for (var row : nums) {
            int rs = row.size();
            total += rs;
            if (rs > maxrs) {
                maxrs = rs;
            }
        }
        var diagonals = new int[nums.size() + maxrs][];
        for (int rown = nums.size() - 1; rown >= 0; rown--) {
            var row = nums.get(rown);
            for (int coln = 0; coln < row.size(); coln++) {
                var sum = rown + coln;
                var val = row.get(coln);
                var diag = diagonals[sum];
                if (diag != null) {
                    if (diag[0] == diag.length) {
                        diag = Arrays.copyOf(diag, (int) (diag.length * 1.4 + 8));
                        diagonals[sum] = diag;
                    }
                    diag[diag[0]++] = val;
                } else {
                    diag = new int[8];
                    diag[0] = 1;
                    diag[diag[0]++] = val;
                    diagonals[sum] = diag;
                }
            }
        }
        int[] result = new int[total];
        int ri = 0;
        for (int[] diag : diagonals) {
            if (diag != null) {
                for (int i = 1; i < diag[0]; i++) {
                    result[ri++] = diag[i];
                }
            }
        }
        return result;
    }

}
