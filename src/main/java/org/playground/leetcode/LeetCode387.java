package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/first-unique-character-in-a-string/submissions/1167137114/">Submission</a>
 * Runtime: 6 ms, Beats 77.01% of users with Java
 * Memory: 44.95 MB, Beats 43.24% of users with Java ( varies greatly between runs )
 */
public class LeetCode387 {
    private final Logger log = LoggerFactory.getLogger(LeetCode387.class);

    public int firstUniqChar(String s) {
        int[] sCharCounts = new int[127];
        for (int i = 0; i < s.length(); i++) {
            sCharCounts[s.charAt(i)]++;
        }
        if (s.length() == 1) {
            return 0;
        }
        for (int i = 0; i < s.length(); i++) {
            if (sCharCounts[s.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }
}
