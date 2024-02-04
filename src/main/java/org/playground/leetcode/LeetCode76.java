package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/minimum-window-substring/">Submission</a>
 * Runtime: 11 ms, Beats 72.01% of users with Java
 * Memory: 43.96 MB, Beats 80.47% of users with Java ( varies greatly between runs )
 */
public class LeetCode76 {
    private final Logger log = LoggerFactory.getLogger(LeetCode76.class);

    public String minWindow(String s, String t) {
        int[] tCharCounts = new int[127];
        for (int i = 0; i < t.length(); i++) {
            tCharCounts[t.charAt(i)]++;
        }
        int bestLength = Integer.MAX_VALUE;
        int bestLengthLeftI = 0;
        int bestLengthRightI = 0;
        int[] candidateCharCounts = new int[127];
        int li = 0;
        int ri = 0;
        while (true) {
            boolean includes = true;
            for (int i = 65; i <= 122; i++) {
                if (candidateCharCounts[i] < tCharCounts[i]) {
                    includes = false;
                    break;
                }
            }
            if (includes) {
                int candidateLength = ri - li;
                if (candidateLength < bestLength) {
                    bestLength = candidateLength;
                    bestLengthLeftI = li;
                    bestLengthRightI = ri;
                }
                candidateCharCounts[s.charAt(li++)]--;
            } else {
                if (ri < s.length()) {
                    candidateCharCounts[s.charAt(ri++)]++;
                } else {
                    int i = 1;
                    break;
                }
            }
        }
        return s.substring(bestLengthLeftI, bestLengthRightI);
    }
}
