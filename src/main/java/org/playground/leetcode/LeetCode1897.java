package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.BitSet;

/**
 * @see <a href="https://leetcode.com/problems/redistribute-characters-to-make-all-strings-equal/submissions/1132516305/">Submission</a>
 * Runtime: 2 ms, Beats 97.27% of users with Java
 * Memory: 42.89 MB, Beats 58.98% of users with Java ( varies greatly between runs )
 */
public class LeetCode1897 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1897.class);

    public boolean makeEqual(String[] words) {
        int[] count = new int[127];
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                count[word.charAt(i)]++;
            }
        }
        // a-z ASCII codes are 97-122
        for (int i = 97; i < 123; i++) {
            if (count[i] % words.length != 0) {
                return false;
            }
        }
        return true;
    }
}
