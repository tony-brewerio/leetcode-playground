package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see <a href="https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/submissions/1118330154/">Submission</a>
 * <p>
 * It works.
 */
public class LeetCode1464 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1464.class);

    public int maxProduct(int[] nums) {
        int max1 = -1; // the largest num
        int max2 = -1; // the second largest num
        for (int num : nums) {
            if (num > max2) {
                max2 = num;
                if (max2 > max1) {
                    int tmax2 = max2;
                    max2 = max1;
                    max1 = tmax2;
                }
            }
        }
        return (max1 - 1) * (max2 - 1);
    }
}
