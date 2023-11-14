package org.playground.leetcode;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/unique-length-3-palindromic-subsequences/submissions/1098954744/">Submission</a>
 * Runtime: 7 ms, Beats 99.50% of users with Java
 * Memory: 44.29 MB, Beats 59.80% of users with Java
 * <p>
 * A much simpler solution based on other recommended solutions.
 * Has a tiny improvement over the average similar solution by calculating boundaries in one go and not per character.
 * Also, using `charAt` is actually faster if there is no need to modify the string, and `toCharArray` is slower for larger strings.
 */
public class LeetCode1930V2 {
    public static final String ASCII_LOWERCASE_LETTERS_STR = "abcdefghijklmnopqrstuvwxyz";

    public int countPalindromicSubsequence(String s) {
        // a-z ASCII codes are 97-122
        var boundaries = new int[2][127];
        Arrays.fill(boundaries[0], -1);
        Arrays.fill(boundaries[1], -1);
        var bli = 0;
        for (int i = 0; i < s.length(); i++) {
            var c = s.charAt(i);
            if (boundaries[0][c] == -1) {
                boundaries[0][c] = i;
                if (++bli == 26) {
                    break;
                }
            }
        }
        var bhi = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            var c = s.charAt(i);
            if (boundaries[1][c] == -1) {
                boundaries[1][c] = i;
                if (++bhi == 26) {
                    break;
                }
            }
        }
        var result = 0;
        for (int outer = 97; outer < 123; outer++) {
            int mc = 0;
            var matches = new boolean[127];
            var bl = boundaries[0][outer];
            var bh = boundaries[1][outer];
            for (int j = (bl + 1); j < bh; j++) {
                var inner = s.charAt(j);
                if (!matches[inner]) {
                    matches[inner] = true;
                    if (++mc == 26) {
                        break;
                    }
                }
            }
            result += mc;
        }
        return result;
    }
}
