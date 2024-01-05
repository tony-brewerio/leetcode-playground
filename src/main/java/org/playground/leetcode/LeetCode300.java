package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/longest-increasing-subsequence/submissions/1137863230/">Submission</a>
 * Runtime: 1194 ms, Beats 5.02% of users with Java
 * Memory: 44.26 MB, Beats 22.77% of users with Java ( varies greatly between runs )
 * <p>
 * Passes the tests, but is really really slow.
 */
public class LeetCode300 {
    private final Logger log = LoggerFactory.getLogger(LeetCode300.class);

    public int lengthOfLIS(int[] nums) {
        int[] cache = new int[nums.length];
        lengthOfLIS(nums, 0, 1, cache);
        int result = 0;
        for (int c : cache) {
            result = Math.max(result, c);
        }
        return result;
    }

    public void lengthOfLIS(int[] nums, int i, int count, int[] cache) {
        if (cache[i] >= count) {
            return;
        }
        cache[i] = count;
        int prev = nums[i];
        for (int j = i + 1; j < nums.length; j++) {
            int num = nums[j];
            if (num > prev) {
                if (cache[j] <= count) {
                    lengthOfLIS(nums, j, count + 1, cache);
                }
            } else{
                if (cache[j] == 0) {
                    lengthOfLIS(nums, j, 1, cache);
                }
            }
        }
    }
}
