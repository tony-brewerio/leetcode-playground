package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/set-mismatch/submissions/1154018204/">Submission</a>
 * Runtime: 2 ms, Beats 97.67% of users with Java
 * Memory: 45.14 MB, Beats 70.30% of users with Java ( varies greatly between runs )
 */
public class LeetCode645 {
    private final Logger log = LoggerFactory.getLogger(LeetCode645.class);

    public int[] findErrorNums(int[] nums) {
        int duplicate = -1;
        boolean[] found = new boolean[nums.length + 1];
        for (int num : nums) {
            if (found[num]) {
                duplicate = num;
            } else {
                found[num] = true;
            }
        }
        for (int i = 1; i <= nums.length; i++) {
            if (!found[i]) {
                return new int[]{duplicate, i};
            }
        }
        throw new IllegalStateException();
    }
}
