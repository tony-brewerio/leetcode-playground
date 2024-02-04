package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/minimum-window-substring/submissions/1165952571/">Submission</a>
 * Runtime: 7 ms, Beats 74.19% of users with Java
 * Memory: 44.22 MB, Beats 73.19% of users with Java ( varies greatly between runs )
 */
public class LeetCode76 {
    private final Logger log = LoggerFactory.getLogger(LeetCode76.class);

    public String minWindow(String s, String t) {
        int[] tCharCounts = new int[127];
        for (int i = 0; i < t.length(); i++) {
            tCharCounts[t.charAt(i)]++;
        }
        List<Integer> tCharsList = new ArrayList<>();
        for (int i = 65; i <= 122; i++) {
            if (tCharCounts[i] > 0) {
                tCharsList.add(i);
            }
        }
        int[] tChars = new int[tCharsList.size()];
        for (int i = 0; i < tCharsList.size(); i++) {
            tChars[i] = tCharsList.get(i);
        }
        int bestLength = Integer.MAX_VALUE;
        int bestLengthLeftI = 0;
        int bestLengthRightI = 0;
        int[] candidateCharCounts = new int[127];
        int li = 0;
        int ri = 0;
        while (true) {
            boolean includes = true;
            for (int c : tChars) {
                if (candidateCharCounts[c] < tCharCounts[c]) {
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
