package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see <a href="https://leetcode.com/problems/determine-if-string-halves-are-alike/submissions/1144434763/">Submission</a>
 * Runtime: 2 ms, Beats 95.53% of users with Java
 * Memory: 42.00 MB, Beats 19.43% of users with Java ( varies greatly between runs )
 */
public class LeetCode1704 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1704.class);

    private static final boolean[] IS_VOWEL = new boolean[127];

    static {
        for (char vowel : new char[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'}) {
            IS_VOWEL[vowel] = true;
        }
    }

    public boolean halvesAreAlike(String s) {
        int h1VowelCount = 0;
        int h2VowelCount = 0;
        int sHalfLength = s.length() / 2;
        int sFullLength = s.length();
        for (int i = 0; i < sHalfLength; i++) {
            if (IS_VOWEL[s.charAt(i)]) {
                h1VowelCount++;
            }
        }
        for (int i = sHalfLength; i < sFullLength; i++) {
            if (IS_VOWEL[s.charAt(i)]) {
                h2VowelCount++;
            }
        }
        return h1VowelCount == h2VowelCount;
    }
}
