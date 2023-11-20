package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see <a href="https://leetcode.com/problems/minimum-amount-of-time-to-collect-garbage/submissions/1103050246/">Submission</a>
 * Runtime: 3 ms, Beats 97.14% of users with Java
 * Memory: 61.34 MB, Beats 62.29% of users with Java
 * <p>
 * Based on a 100% solution from Leetcode.
 * Makes total sense, since iterating from the back like this potentially avoids a ton of string contains checks.
 */
public class LeetCode2931V2 {
    private final Logger log = LoggerFactory.getLogger(LeetCode2931V2.class);

    public int garbageCollection(String[] garbage, int[] travel) {
        // max cost assuming all trucks travel to the end
        var total = 0;
        for (String g : garbage) {
            total += g.length();
        }
        for (int t : travel) {
            total += t * 3;
        }
        //
        outer:
        for (char c : new char[]{'M', 'P', 'G'}) {
            int tcost = 0;
            for (int i = garbage.length - 1; i > 0; i--) {
                if (garbage[i].indexOf(c) != -1) {
                    total -= tcost;
                    continue outer;
                }
                tcost += travel[i - 1];
            }
            total -= tcost;
        }
        return total;
    }
}
