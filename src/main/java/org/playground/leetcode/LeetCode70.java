package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see <a href="https://leetcode.com/problems/climbing-stairs/submissions/1149963388/">Submission</a>
 */
public class LeetCode70 {
    private final Logger log = LoggerFactory.getLogger(LeetCode70.class);

    public int climbStairs(int n) {
        int[] cache = new int[n + 1];
        return climbStairs(n, cache);
    }

    private int climbStairs(int n, int[] cache) {
        if (n == 0) {
            return 1;
        }
        int cached = cache[n];
        if (cached != 0) {
            return cached;
        } else {
            int result = climbStairs(n - 1, cache);
            if (n >= 2) {
                result += climbStairs(n - 2, cache);
            }
            return cache[n] = result;
        }
    }
}
