package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/count-of-matches-in-tournament/submissions/1113139913/">Submission</a>
 * <p>
 * It works.
 */
public class LeetCode1688 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1688.class);

    public int numberOfMatches(int n) {
        int matches = 0;
        while (n > 1) {
            int nd = n / 2;
            int nm = n % 2;
            n = nd;
            matches += nd + nm;
        }
        return matches;
    }
}
