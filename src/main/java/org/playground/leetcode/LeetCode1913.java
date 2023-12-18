package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see <a href="https://leetcode.com/problems/maximum-product-difference-between-two-pairs/submissions/1122859312/">Submission</a>
 * Runtime: 1 ms, Beats 98.22% of users with Java
 * Memory: 43.78 MB, Beats 91.74% of users with Java ( varies greatly between runs )
 * <p>
 * Pretty easy one. We know that the largest product is between two largest numbers, and the samllest one between smallest numbers.
 * So one iteration over the list to find two largest and two smallest numbers is enough.
 */
public class LeetCode1913 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1913.class);

    public int maxProductDifference(int[] nums) {
        int max1 = Integer.MIN_VALUE; // the largest num
        int max2 = Integer.MIN_VALUE; // the second largest num
        int min1 = Integer.MAX_VALUE; // the smallest num
        int min2 = Integer.MAX_VALUE; // the second smallest num
        for (int num : nums) {
            if (num > max2) {
                max2 = num;
                if (max2 > max1) {
                    int tmax2 = max2;
                    max2 = max1;
                    max1 = tmax2;
                }
            }
            if (num < min2) {
                min2 = num;
                if (min2 < min1) {
                    int tmin2 = min2;
                    min2 = min1;
                    min1 = tmin2;
                }
            }
        }
        return (max1 * max2) - (min1 * min2);
    }
}
