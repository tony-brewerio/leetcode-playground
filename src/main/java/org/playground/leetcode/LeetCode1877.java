package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/submissions/">Submission</a>
 * <p>
 * It works.
 */
public class LeetCode1877 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1877.class);

    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int maxPairSum = 0;
        for (int i = 0; i < nums.length / 2; i++) {
            int sum = nums[i] + nums[nums.length - i - 1];
            if (sum > maxPairSum) {
                maxPairSum = sum;
            }
        }
        return maxPairSum;
    }
}
