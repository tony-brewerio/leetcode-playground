package org.playground.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/arithmetic-subarrays/submissions/1105070814/">Submission</a>
 * Runtime: 20 ms, Beats 39.39% of users with Java
 * Memory: 44.58 MB, Beats 25.62% of users with Java
 * <p>
 * Pretty simple one, got accepted on the first try.
 * Though performance is not good for some reason. Maybe there's a good way to avoid sorting?
 */
public class LeetCode1630 {

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        var result = new ArrayList<Boolean>();
        for (int i = 0; i < l.length; i++) {
            var arr = Arrays.copyOfRange(nums, l[i], r[i] + 1);
            Arrays.sort(arr);
            boolean ok = true;
            int match = arr[1] - arr[0];
            for (int j = 1; j < (arr.length - 1); j++) {
                if ((arr[j + 1] - arr[j]) != match) {
                    ok = false;
                    break;
                }
            }
            result.add(ok);
        }
        return result;
    }

}
