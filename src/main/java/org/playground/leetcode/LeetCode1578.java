package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see <a href="https://leetcode.com/problems/minimum-time-to-make-rope-colorful/submissions/1129936811/">Submission</a>
 * Runtime: 11 ms, Beats 27.51% of users with Java
 * Memory: 60.72 MB, Beats 15.68% of users with Java ( varies greatly between runs )
 * <p>
 * A bit slow, but works fine.
 * Can probably be simplifed by comparing two adjacent colors?
 */
public class LeetCode1578 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1578.class);

    public int minCost(String colors, int[] neededTime) {
        int result = 0;
        char prev = 0;
        int samecount = 0;
        int maxcost = 0;
        int sumcost = 0;
        for (int i = 0; i < colors.length(); i++) {
            char color = colors.charAt(i);
            int cost = neededTime[i];
            if (color == prev) {
                samecount++;
                maxcost = cost > maxcost ? cost : maxcost;
                sumcost += cost;
            } else {
                if (samecount > 1) {
                    result += sumcost - maxcost;
                }
                prev = color;
                samecount = 1;
                maxcost = cost;
                sumcost = cost;
            }
        }
        if (samecount > 1) {
            result += sumcost - maxcost;
        }
        return result;
    }
}
