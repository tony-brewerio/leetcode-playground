package org.playground.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/arithmetic-subarrays/submissions/1105078475/">Submission</a>
 * Runtime: 8 ms, Beats 98.35% of users with Java
 * Memory: 44.20 MB, Beats 61.71% of users with Java
 * <p>
 * Added a couple of tricks.
 * First, end early if we know for sure that max-min is now divisible by subarray length.
 * Second, allocate a single buf array in advance and reuse it, instead of new one all the time.
 * The last one is probably useless, but maybe not.
 * The end solution is pretty fast, though not that memory efficient.
 */
public class LeetCode1630 {

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        var buf = Arrays.copyOf(nums, nums.length);
        var result = new ArrayList<Boolean>();
        for (int i = 0; i < l.length; i++) {
            var min = Integer.MAX_VALUE;
            var max = Integer.MIN_VALUE;
            for (int j = l[i]; j < (r[i] + 1); j++) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
            }
            if (min == max) {
                result.add(true);
                continue;
            }
            if ((max - min) % (r[i] - l[i]) != 0) {
                result.add(false);
                continue;
            }
            //
            for (int j = l[i]; j < (r[i] + 1); j++) {
                buf[j] = nums[j];
            }
            Arrays.sort(buf, l[i], r[i] + 1);
            boolean ok = true;
            int match = buf[l[i] + 1] - buf[l[i]];
            for (int j = l[i] + 1; j < r[i]; j++) {
                if ((buf[j + 1] - buf[j]) != match) {
                    ok = false;
                    break;
                }
            }
            result.add(ok);
        }
        return result;
    }

}
