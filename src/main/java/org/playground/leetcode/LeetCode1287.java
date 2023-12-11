package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/element-appearing-more-than-25-in-sorted-array/submissions/1117539687/">Submission</a>
 * Runtime: 0 ms, Beats 100.00% of users with Java
 * Memory: 43.02 MB, Beats 81.99% of users with Java
 * <p>
 * Uses binary search for larger arrays.
 * Feel like its not entirely optimal, since there is some regular iteration left there, but is better than global counter.
 */
public class LeetCode1287 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1287.class);

    public int findSpecialInteger(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }
        if (arr.length <= 8) {
            return findSpecialIntegerForSmallArray(arr);
        }
        return findSpecialIntegerForBigArray(arr);
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

    private int findSpecialIntegerForBigArray(int[] arr) {
        int threshold = (int) Math.floor(arr.length / 4.0d) + 1;
        int[] candidates = new int[]{
                arr[1 * (arr.length / 4)],
                arr[2 * (arr.length / 4)],
                arr[3 * (arr.length / 4)],
        };
        // early exit
        if (arr[0] == arr[threshold - 1]) {
            return arr[0];
        }
        if (arr[arr.length - 1] == arr[arr.length - threshold]) {
            return arr[arr.length - 1];
        }
        // binary search
        // first item special case
        for (int candidate : candidates) {
            int lower = Arrays.binarySearch(arr, candidate - 1);
            if (lower < 0) {
                lower = Math.abs(lower + 1);
            } else {
                while (arr[lower] != candidate) {
                    lower++;
                }
            }
            int upper = Arrays.binarySearch(arr, candidate + 1);
            if (upper < 0) {
                upper = Math.abs(upper + 1);
            } else {
                while (arr[upper - 1] != candidate) {
                    upper--;
                }
            }
            if ((upper - lower) >= threshold) {
                return candidate;
            }
        }
        return -1;
    }
}
