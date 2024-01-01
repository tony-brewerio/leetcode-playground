package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/assign-cookies/submissions/1134101718/">Submission</a>
 * Runtime: 8 ms, Beats 98.87% of users with Java
 * Memory: 45.30 MB, Beats 14.61% of users with Java ( varies greatly between runs )
 */
public class LeetCode1455 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1455.class);

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int result = 0;
        int si = 0;
        for (int i = 0; i < g.length; i++) {
            var greed = g[i];
            while (si < s.length && s[si] < greed) {
                si++;
            }
            if (si < s.length) {
                result++;
                si++;
            } else {
                break;
            }
        }
        return result;
    }
}
