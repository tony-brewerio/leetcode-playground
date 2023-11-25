package org.playground.leetcode;

/**
 * @see <a href="https://leetcode.com/problems/sum-of-absolute-differences-in-a-sorted-array/submissions/1106042427/">Submission</a>
 * Runtime: 5 ms, Beats 25.64% of users with Java
 * Memory: 57.71 MB, Beats 60.26% of users with Java
 * <p>
 * Feels like it should be faster, comparatively speaking, but perhaps not.
 * Maybe there's a way to use only one sums array?
 * <p>
 * Indeed, it is possible to have just one sums array.
 * Still a bit slow though?
 */
public class LeetCode1685 {

    public int[] getSumAbsoluteDifferences(int[] nums) {
        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = nums[i] + sums[i - 1];
        }
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) {
                result[i] += nums[i] * i - sums[i - 1];
            }
            if (i < (nums.length - 1)) {
                result[i] += (sums[nums.length - 1] - sums[i]) - nums[i] * (nums.length - 1 - i);
            }
        }
        return result;
    }

}
