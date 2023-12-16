package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/valid-anagram/submissions/1120979111/">Submission</a>
 * Runtime: 4 ms, Beats 73.04% of users with Java
 * Memory: 41.88 MB, Beats 88.76% of users with Java ( varies greatly between runs )
 * <p>
 * Simple enough - count unique chars in each string and compare the counts.
 * Somehow, this is slow-ish by Leetcode standards.
 */
public class LeetCode242 {
    private final Logger log = LoggerFactory.getLogger(LeetCode242.class);

    public boolean isAnagram(String s, String t) {
        var sfreqs = new int[26];
        for (int i = 0; i < s.length(); i++) {
            sfreqs[s.charAt(i) - 97]++;
        }
        //
        var tfreqs = new int[26];
        for (int i = 0; i < t.length(); i++) {
            tfreqs[t.charAt(i) - 97]++;
        }
        //
        for (int i = 0; i < 25; i++) {
            if (sfreqs[i] != tfreqs[i]) {
                return false;
            }
        }
        return true;
    }

}
