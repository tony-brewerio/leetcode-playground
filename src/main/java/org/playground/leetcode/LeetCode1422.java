package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see <a href="https://leetcode.com/problems/maximum-score-after-splitting-a-string/submissions/1126132117/">Submission</a>
 * Runtime: 1 ms, Beats 97.83% of users with Java
 * Memory: 41.24 MB, Beats 34.78% of users with Java ( varies greatly between runs )
 */
public class LeetCode1422 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1422.class);

    public int maxScore(String s) {
        int[] ones = new int[s.length()];
        int accum = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                accum++;
            }
            ones[i] = accum;
        }
        accum = 0;
        int max = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') {
                accum++;
            }
            int score = accum + ones[i + 1];
            if (score > max) {
                max = score;
            }
        }
        return max;
    }
}
