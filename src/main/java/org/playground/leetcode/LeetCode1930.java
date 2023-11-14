package org.playground.leetcode;

import java.util.BitSet;

/**
 *
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
        var palindromes = new BitSet();
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
                palindromes.set((c1 << 8) + c2);
            }
        }
        int result = 0;
        for (var i = palindromes.nextSetBit(0); i != -1; i = palindromes.nextSetBit(i + 1)) {
            result++;
        }
        return result;
    }
}
