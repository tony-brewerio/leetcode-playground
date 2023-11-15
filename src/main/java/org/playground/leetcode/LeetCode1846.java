package org.playground.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @see <a href="https://leetcode.com/problems/number-of-restricted-paths-from-first-to-last-node/submissions/1097267570/">Submission</a>
 * Runtime: 6 ms, Beats 81.88% of users with Java
 * Memory: 56.56 MB, Beats 28.26% of users with Java
 * <p>
 * Don't want to spend more time on this.
 * The most obvious solution works well enough.
 * This can be solved without sorting perhaps.
 */
public class LeetCode1846 {

    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        arr[0] = 1;
        for (int j = 0, i = 1; i < arr.length; i++, j++) {
            if ((arr[i] - arr[j]) > 1) {
                arr[i] = arr[j] + 1;
            }
        }
        return arr[arr.length - 1];
    }

}
