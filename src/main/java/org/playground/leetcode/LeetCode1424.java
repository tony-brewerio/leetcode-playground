package org.playground.leetcode;

import org.playground.leetcode.helpers.AveragingOperationTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/diagonal-traverse-ii/submissions/1104483391/">Submission</a>
 * not accepted
 * <p>
 * Would be cute if worked, but alas.
 * Leetcode apparently lied about the constraints.
 * They say that `nums[i][j] <= 10^5`, but that's not actually true, and test case 53 contains much larger values.
 */
public class LeetCode1424 {

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        // max long is 9,223,372,036,854,775,807
        //               200,000,100,000,100,000
        // max item 100,000
        // max rows 100,000
        // max cols 100,000
        // max sum  200,000
        // six digits for sum, six digits for row, six digits for value
        // 200,000,100,000,100,000
        int total = 0;
        for (int y = 0; y < nums.size(); y++) {
            total += nums.get(y).size();
        }
        var longs = new long[total];
        long maxval = 100_000;
        long s_mult = 1_000_000_000_000L;
        long y_mult = 1_000_000L;
        int li = 0;
        for (int y = 0; y < nums.size(); y++) {
            var row = nums.get(y);
            for (int x = 0; x < row.size(); x++) {
                longs[li++] = ((x + y) * s_mult) + ((maxval - y) * y_mult) + row.get(x);
            }
        }
        Arrays.sort(longs);
        var ints = new int[longs.length];
        for (int i = 0; i < longs.length; i++) {
            ints[i] = (int) (longs[i] % y_mult);
        }
        return ints;
    }
}
