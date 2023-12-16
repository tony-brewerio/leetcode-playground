package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/valid-anagram/submissions/1120981796/">Submission</a>
 * Runtime: 3 ms, Beats 89.36% of users with Java
 * Memory: 42.19 MB, Beats 78.74% of users with Java ( varies greatly between runs )
 * <p>
 * Simple enough - count unique chars in each string and compare the counts.
 * Somehow, this is slow-ish by Leetcode standards.
 * <p>
 * Probably just a run to run variance, but using 127 arrays and avoiding a minus in each iteration seems to help.
 */
public class LeetCode242 {
    private final Logger log = LoggerFactory.getLogger(LeetCode242.class);

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        //
        var sfreqs = new int[127];
        for (int i = 0; i < s.length(); i++) {
            sfreqs[s.charAt(i)]++;
        }
        //
        var tfreqs = new int[127];
        for (int i = 0; i < t.length(); i++) {
            tfreqs[t.charAt(i)]++;
        }
        //
        for (int i = 97; i < 123; i++) {
            if (sfreqs[i] != tfreqs[i]) {
                return false;
            }
        }
        return true;
    }

}
