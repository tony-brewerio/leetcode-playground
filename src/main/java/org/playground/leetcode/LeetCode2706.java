package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see <a href="https://leetcode.com/problems/buy-two-chocolates/submissions/1124522906/">Submission</a>
 * Runtime: 1 ms, Beats 100.00% of users with Java
 * Memory: 44.16 MB, Beats 5.53% of users with Java ( varies greatly between runs )
 * <p>
 * There were multiple problems with similar min/max solutions in the last week, huh.
 */
public class LeetCode2706 {
    private final Logger log = LoggerFactory.getLogger(LeetCode2706.class);

    public int buyChoco(int[] prices, int money) {
        int min1 = Integer.MAX_VALUE; // the smallest num
        int min2 = Integer.MAX_VALUE; // the second smallest num
        for (int num : prices) {
            if (num < min2) {
                min2 = num;
                if (min2 < min1) {
                    int tmin2 = min2;
                    min2 = min1;
                    min1 = tmin2;
                }
            }
        }
        int cost = min1 + min2;
        return cost > money ? money : money - cost;
    }
}
