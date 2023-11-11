package org.playground.leetcode;

/**
 * @see <a href="https://leetcode.com/problems/minimum-elements-to-add-to-form-a-given-sum/submissions/1096251541/">Submission</a>
 */
public class LeetCode1785 {

    public int minElements(int[] nums, int limit, int goal) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < goal) {
            long delta = Math.abs(goal - sum);
            return (int) ((delta / limit) + (delta % limit != 0 ? 1 : 0));
        }
        if (sum > goal) {
            long delta = Math.abs(sum - goal);
            return (int) ((delta / limit) + (delta % limit != 0 ? 1 : 0));
        }
        return 0;
    }

}
