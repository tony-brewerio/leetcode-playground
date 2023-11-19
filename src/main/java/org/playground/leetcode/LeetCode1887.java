package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/reduction-operations-to-make-the-array-elements-equal/submissions/1102279409/">Submission</a>
 * <p>
 * This one seems fairly obvious.
 * Go over the sorted array and, every time there's a step up, add to the cost.
 */
public class LeetCode1887 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1887.class);

    public int reductionOperations(int[] nums) {
        Arrays.sort(nums);
        int cost = 0;
        for (int i = 0; i < (nums.length - 1); i++) {
            if (nums[i] < nums[i + 1]) {
                cost += nums.length - 1 - i;
            }
        }
        return cost;
    }
}
