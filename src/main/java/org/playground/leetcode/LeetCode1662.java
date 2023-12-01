package org.playground.leetcode;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/submissions/1110443984/">Submission</a>
 * Runtime: 10 ms, Beats 7.84% of users with Java
 * Memory: 41.56 MB, Beats 5.03% of users with Java
 * <p>
 * Pretty nice solution with zipped streams, but is slow-ish.
 */
public class LeetCode1662 {

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        var w1 = Arrays.stream(word1).flatMapToInt(String::chars).iterator();
        var w2 = Arrays.stream(word2).flatMapToInt(String::chars).iterator();
        while (true) {
            var w1n = w1.hasNext();
            var w2n = w2.hasNext();
            if (w1n != w2n) {
                return false;
            }
            if (w1n) {
                if (w1.nextInt() != w2.nextInt()) {
                    return false;
                }
            } else {
                return true;
            }
        }
    }

}
