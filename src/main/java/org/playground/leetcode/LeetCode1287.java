package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see <a href="https://leetcode.com/problems/element-appearing-more-than-25-in-sorted-array/submissions/1117527556/">Submission</a>
 * <p>
 * Naive solution, iterates and counts occurences.
 */
public class LeetCode1287 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1287.class);

    public int findSpecialInteger(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }
        return findSpecialIntegerForSmallArray(arr);
    }

    private int findSpecialIntegerForSmallArray(int[] arr) {
        int threshold = (int) Math.floor(arr.length / 4.0d) + 1;
        int current = arr[0];
        int count = 1;
        for (int i = 1; i < arr.length; i++) {
            var n = arr[i];
            if (n == current) {
                if (++count >= threshold) {
                    return n;
                }
            } else {
                current = n;
                count = 1;
            }
        }
        return -1;
    }
}
