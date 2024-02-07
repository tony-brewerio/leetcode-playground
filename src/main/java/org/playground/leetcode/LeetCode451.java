package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @see <a href="https://leetcode.com/problems/sort-characters-by-frequency/submissions/1169113856/">Submission</a>
 * Runtime: 6 ms, Beats 92.52% of users with Java
 * Memory: 44.50 MB, Beats 91.94% of users with Java ( varies greatly between runs )
 */
public class LeetCode451 {
    private final Logger log = LoggerFactory.getLogger(LeetCode451.class);

    public String frequencySort(String s) {
        int[] counts = new int[127];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i)]++;
        }
        int[][] charsWithCounts = new int[10 + 26 + 26][2];
        for (int i = 0; i < 10; i++) {
            int c = i + '0';
            charsWithCounts[i][0] = c;
            charsWithCounts[i][1] = counts[c];
        }
        for (int i = 0; i < 26; i++) {
            int c = i + 'A';
            charsWithCounts[10 + i][0] = c;
            charsWithCounts[10 + i][1] = counts[c];
        }
        for (int i = 0; i < 26; i++) {
            int c = i + 'a';
            charsWithCounts[10 + 26 + i][0] = c;
            charsWithCounts[10 + 26 + i][1] = counts[c];
        }
        Arrays.sort(charsWithCounts, (a, b) -> b[1] - a[1]);
        char[] cb = new char[s.length()];
        int cbi = 0;
        for (int[] cwc : charsWithCounts) {
            Arrays.fill(cb, cbi, cbi + cwc[1], (char) cwc[0]);
            cbi += cwc[1];
        }
        return new String(cb);
    }
}
