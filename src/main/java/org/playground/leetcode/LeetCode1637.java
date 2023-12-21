package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @see <a href="https://leetcode.com/problems/widest-vertical-area-between-two-points-containing-no-points/submissions/1125360683/">Submission</a>
 * Runtime: 13 ms, Beats 98.07% of users with Java
 * Memory: 73.10 MB, Beats 5.02% of users with Java ( varies greatly between runs )
 * <p>
 * People don't like this one and I'll agree.
 * The actual problem is really simple and wants you to find maximum different between elements in a sorted array.
 * However, the description is almost intenionally made to make you think there's more to it at first.
 * Like, the y coordinate of the points literally does not matter.
 * Also, sorting a separate flat array is way faster than sorting the original array using comparator.
 */
public class LeetCode1637 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1637.class);

    public int maxWidthOfVerticalArea(int[][] points) {
        int[] xs = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            xs[i] = points[i][0];
        }
        Arrays.sort(xs);
        int max = 0;
        for (int i = 1; i < points.length; i++) {
            int delta = xs[i] - xs[i - 1];
            if (delta > max) {
                max = delta;
            }
        }
        return max;
    }
}
