package org.playground.leetcode;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/count-nice-pairs-in-an-array/submissions/1103746838/">Submission</a>
 * Runtime: 17 ms, Beats 100.00% of users with Java
 * Memory: 54.58 MB, Beats 69.87% of users with Java
 * <p>
 * Got a working solution that timed out, then took one hint that made it all very obvious.
 * With the modified nice pair condition, it is clear that we need to calculate num - rev for the entire array.
 * And then find how many ordered combinations can be made within each unique number group.
 * Do do that, sort the numbers and iterate over them, keeping unique item count, then add up possible combinations.
 * Feels weird that this does so well relatively among other solutions.
 * <p>
 * I have updated the solution to reuse incoming nums array in attempt to save some memory.
 * This is a bad coding practice in any normal environment.
 */
public class LeetCode1814 {
    private static final long MODULO = (long) (Math.pow(10, 9) + 7);

    public int countNicePairs(int[] nums) {
        // nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
        // -> (hint)
        // nums[i] - rev(nums[i]) == nums[j] - rev(nums[j])
        // note - this reuses the input array to save some memory bytes, which is a very bad thing, but them bytes
        for (int i = 0; i < nums.length; i++) {
            var n = nums[i];
            int rev = 0;
            while (n > 0) {
                rev = rev * 10 + (n % 10);
                n = n / 10;
            }
            nums[i] = nums[i] - rev;
        }
        Arrays.sort(nums);
        //
        long total = 0;
        int curr = nums[0];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            var d = nums[i];
            if (curr == d) {
                count++;
            }
            if (d != curr || i == (nums.length - 1)) {
                long combinations = 0;
                while (count >= 2) {
                    combinations += count - 1;
                    count--;
                }
                total += combinations;
                curr = d;
            }
        }
        return (int) (total % MODULO);
    }
}
