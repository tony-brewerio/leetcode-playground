package org.playground.leetcode;

/**
 * @see <a href="https://leetcode.com/problems/sum-of-absolute-differences-in-a-sorted-array/submissions/1106042427/">Submission</a>
 * Runtime: 4 ms, Beats 65.38% of users with Java
 * Memory: 56.18 MB, Beats 94.87% of users with Java
 * <p>
 * Feels like it should be faster, comparatively speaking, but perhaps not.
 * Maybe there's a way to use only one sums array?
 * <p>
 * Indeed, it is possible to have just one sums array.
 * Still a bit slow though?
 * <p>
 * Now that I think about it, without separate right hand sum array, there is no need for a sums array per se.
 * Since we iterate from the left side, we can simply accumulate current sum while iterating.
 * Still need to calculate max sum for right hand calculation, but no need for an array.
 * <p>
 * This is the cursed version, modifying the input array.
 * At 4 ms it is probably withing a margin of error from 3 ms which seems to be the best you can get.
 */
public class LeetCode1685 {

    public int[] getSumAbsoluteDifferences(int[] nums) {
        var total = 0;
        for (int num : nums) {
            total += num;
        }
        int accum = 0;
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            nums[i] = 0;
            if (i > 0) {
                nums[i] += n * i - accum;
            }
            if (i < (nums.length - 1)) {
                nums[i] += (total - accum - n) - n * (nums.length - 1 - i);
            }
            accum += n;
        }
        return nums;
    }

}
