package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/rearrange-array-elements-by-sign/submissions/1175388162/">Submission</a>
 * Runtime: 4 ms, Beats 57.10% of users with Java
 * Memory: 85.64 MB, Beats 6.21% of users with Java ( varies greatly between runs )
 * <p>
 * This is totally doable by advancing two pointers over the nums independently, which avoids extra two arrays.
 * Using two arrays requires less comparison operations, but two pointers will likely be faster still.
 */
public class LeetCode2149 {
    private final Logger log = LoggerFactory.getLogger(LeetCode2149.class);

    public int[] rearrangeArray(int[] nums) {
        int[] positive = new int[nums.length / 2];
        int[] negative = new int[nums.length / 2];
        int pi = 0;
        int ni = 0;
        for (int num : nums) {
            if (num > 0) {
                positive[pi++] = num;
            } else {
                negative[ni++] = num;
            }
        }
        int[] result = new int[nums.length];
        for (int i = 0, j = 0; i < positive.length; i++, j += 2) {
            result[j] = positive[i];
            result[j + 1] = negative[i];
        }
        return result;
    }
}
