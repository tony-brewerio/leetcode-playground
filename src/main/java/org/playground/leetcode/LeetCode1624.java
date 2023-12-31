package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/largest-substring-between-two-equal-characters/submissions/1133161411/">Submission</a>
 * Runtime: 1 ms, Beats 86.22% of users with Java
 * Memory: 41.55 MB, Beats 8.77% of users with Java ( varies greatly between runs )
 */
public class LeetCode1624 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1624.class);

    public int maxLengthBetweenEqualCharacters(String s) {
        int result = -1;
        int[] idx = new int[127];
        Arrays.fill(idx, -1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int first = idx[c];
            if (first != -1) {
                result = Math.max(result, i - first - 1);
            } else {
                idx[c] = i;
            }
        }
        return result;
    }
}
