package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/number-of-laser-beams-in-a-bank/submissions/1135877882/">Submission</a>
 * Runtime: 12 ms, Beats 84.33% of users with Java
 * Memory: 44.98 MB, Beats 25.79% of users with Java ( varies greatly between runs )
 * <p>
 * How the heck is this a "Medium" problem, just way too easy.
 */
public class LeetCode2125 {
    private final Logger log = LoggerFactory.getLogger(LeetCode2125.class);

    public int numberOfBeams(String[] bank) {
        int result = 0;
        int prev = 0;
        for (String row : bank) {
            int count = 0;
            for (int i = 0; i < row.length(); i++) {
                if (row.charAt(i) == '1') {
                    count++;
                }
            }
            if (count > 0) {
                result += prev * count;
                prev = count;
            }
        }
        return result;
    }
}
