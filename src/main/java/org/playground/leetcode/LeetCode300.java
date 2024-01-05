package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/longest-increasing-subsequence/submissions/1137880723/">Submission</a>
 * Runtime: 54 ms, Beats 27.39% of users with Java
 * Memory: 43.81 MB, Beats 38.65% of users with Java ( varies greatly between runs )
 * <p>
 * Passes the tests, but is really really slow.
 * <p>
 * The second version is the same thing but simplified and reduced to a nested for loop.
 * Is still slow-ish, but good enough.
 */
public class LeetCode300 {
    private final Logger log = LoggerFactory.getLogger(LeetCode300.class);

    public int lengthOfLIS(int[] nums) {
        int[] cache = new int[nums.length];
        Arrays.fill(cache, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (cache[i] >= cache[j] && nums[i] < nums[j]) {
                    cache[j] = cache[i] + 1;
                }
            }
        }
        int result = 1;
        for (int count : cache) {
            if (count > result) {
                result = count;
            }
        }
        return result;
    }

}
