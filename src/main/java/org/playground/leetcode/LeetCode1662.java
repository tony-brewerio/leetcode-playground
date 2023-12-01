package org.playground.leetcode;

/**
 * @see <a href="https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/submissions/1110447313/">Submission</a>
 * Runtime: 2 ms, Beats 12.10% of users with Java
 * Memory: 40.69 MB, Beats 29.21% of users with Java
 * <p>
 * Not as pretty as the stream solution, but works much faster and consumes less memory.
 * Supposedly, concatenation + comparison solution is even faster, but it sounds boring.
 */
public class LeetCode1662 {

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int w1tl = 0;
        for (String s : word1) {
            w1tl += s.length();
        }
        int w2tl = 0;
        for (String s : word2) {
            w2tl += s.length();
        }
        if (w1tl != w2tl) {
            return false;
        }
        int w1wi = 0, w1ci = 0;
        int w2wi = 0, w2ci = 0;
        for (int i = 0; i < w1tl; i++) {
            var w1 = word1[w1wi];
            var w2 = word2[w2wi];
            if (w1.charAt(w1ci++) != w2.charAt(w2ci++)) {
                return false;
            }
            if (w1ci >= w1.length()) {
                w1wi++;
                w1ci = 0;
            }
            if (w2ci >= w2.length()) {
                w2wi++;
                w2ci = 0;
            }
        }
        return true;
    }

}
