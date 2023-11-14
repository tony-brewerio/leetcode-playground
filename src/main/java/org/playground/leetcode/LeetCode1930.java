package org.playground.leetcode;

import java.util.BitSet;

/**
 * @see <a href="https://leetcode.com/problems/unique-length-3-palindromic-subsequences/submissions/1098923650/">Submission</a>
 * Runtime: 15 ms, Beats 99.01% of users with Java
 * Memory: 44.34 MB, Beats 52.85% of users with Java
 * <p>
 * The approach is very similar to a counting sort.
 * First, do a pass over the string to record which positions for all characters.
 * After that, we can simple loop over all possible combinations and check if they can be found based on positions.
 * Runs really fast, and fairly efficient.
 */
public class LeetCode1930 {
    public static final String ASCII_LOWERCASE_LETTERS_STR = "abcdefghijklmnopqrstuvwxyz";

    public int countPalindromicSubsequence(String s) {
        // a-z ASCII codes are 97-122
        BitSet[] positions = new BitSet[123];
        for (int i = 97; i < 123; i++) {
            positions[i] = new BitSet(s.length());
        }
        // mark position indexes by char
        for (int i = 0; i < s.length(); i++) {
            positions[s.charAt(i)].set(i);
        }
        //
        var palindromes = new BitSet(127 * 127);
        for (char c1 = 97; c1 < 123; c1++) {
            var c1p = positions[c1];
            if (c1p.isEmpty()) {
                continue;
            }
            var c1i = c1p.nextSetBit(0);
            for (char c2 = 97; c2 < 123; c2++) {
                var c2p = positions[c2];
                if (c2p.isEmpty()) {
                    continue;
                }
                int c2i = c2p.nextSetBit(c1i + 1);
                if (c2i == -1) {
                    continue;
                }
                int c3i = c1p.nextSetBit(c2i + 1);
                if (c3i == -1) {
                    continue;
                }
                palindromes.set((c1 << 7) + c2);
            }
        }
        int result = 0;
        for (var i = palindromes.nextSetBit(0); i != -1; i = palindromes.nextSetBit(i + 1)) {
            result++;
        }
        return result;
    }
}
