package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see <a href="https://leetcode.com/problems/minimum-time-to-make-rope-colorful/submissions/1129968672/">Submission</a>
 * Runtime: 6 ms, Beats 93.50% of users with Java
 * Memory: 61.5 MB, Beats 11% of users with Java ( varies greatly between runs )
 * <p>
 * A bit slow, but works fine.
 * Can probably be simplifed by comparing two adjacent colors?
 * <p>
 * Played around with another approach.
 * Converting string to raw char array makes it more efficient to access characters.
 * I have also reduced the amount of array access operations.
 * It is now fast, but ugly.
 */
public class LeetCode1578 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1578.class);

    public int minCost(String colors, int[] neededTime) {
        char[] colorsAsArray = colors.toCharArray();
        int result = 0;
        int cl = colorsAsArray.length;
        char color1 = colorsAsArray[0];
        int cost1 = neededTime[0];
        char color2;
        int cost2;
        for (int i = 1; i < cl; i++) {
            color2 = colorsAsArray[i];
            cost2 = neededTime[i];
            if (color1 == color2) {
                if (cost1 <= cost2) {
                    result += cost1;
                    cost1 = cost2;
                } else {
                    result += cost2;
                }
                color1 = color2;
            } else {
                color1 = color2;
                cost1 = cost2;
            }
        }
        return result;
    }
}
