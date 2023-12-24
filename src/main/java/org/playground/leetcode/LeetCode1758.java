package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see <a href="https://leetcode.com/problems/minimum-changes-to-make-alternating-binary-string/submissions/1127351507/">Submission</a>
 * Runtime: 4 ms, Beats 74.75% of users with Java
 * Memory: 42.42 MB, Beats 6.56% of users with Java ( varies greatly between runs )
 */
public class LeetCode1758 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1758.class);

    public int minOperations(String s) {
        int delta = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != (i % 2 == 0 ? '0' : '1')) {
                delta++;
            }
        }
        return Math.min(delta, s.length() - delta);
    }
}
