package org.playground.leetcode;

/**
 * @see <a href="https://leetcode.com/problems/sum-of-absolute-differences-in-a-sorted-array/submissions/1106039887/">Submission</a>
 * Runtime: 7 ms, Beats 12.82% of users with Java
 * Memory: 60.05 MB, Beats 5.77% of users with Java
 * <p>
 * Feels like it should be faster, comparatively speaking, but perhaps not.
 * Maybe there's a way to use only one sums array?
 */
public class LeetCode1685 {

    public int[] getSumAbsoluteDifferences(int[] nums) {
        int[] sumsl = new int[nums.length];
        int[] sumsr = new int[nums.length];
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int li = i;
            int ri = nums.length - i - 1;
            sumsl[li] = nums[li] + (i == 0 ? 0 : sumsl[li - 1]);
            sumsr[ri] = nums[ri] + (ri == (nums.length - 1) ? 0 : sumsr[ri + 1]);
        }
        for (int i = 0; i < sumsl.length; i++) {
            if (i > 0) {
                result[i] += nums[i] * i - sumsl[i - 1];
            }
            if (i < (nums.length - 1)) {
                result[i] += sumsr[i + 1] - nums[i] * (nums.length - 1 - i);
            }
        }
        return result;
    }

}
