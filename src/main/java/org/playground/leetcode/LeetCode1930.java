package org.playground.leetcode;

import java.util.*;

/**
 */
public class LeetCode1930 {

    public int countPalindromicSubsequence(String s) {
        // a-z ASCII codes are 97-122
        List<BitSet> positions = new ArrayList<>(123);
        for (int i = 0; i < 123; i++) {
            positions.add(i >= 97 ? new BitSet(s.length()) : null);
        }
        // mark position indexes by char
        for (int i = 0; i < s.length(); i++) {
            positions.get(s.charAt(i)).set(i);
        }
        //
        var palindromes = new HashSet<Palindrome>((int) Math.pow(123 - 97, 3));
        //
        for (char c1 = 97; c1 < 123; c1++) {
            var c1p = positions.get(c1);
            if (c1p.isEmpty()) {
                continue;
            }
            var c1i = c1p.nextSetBit(0);
            for (char c2 = 97; c2 < 123; c2++) {
                var c2p = positions.get(c2);
                if (c2p.isEmpty()) {
                    continue;
                }
                int c2i = c2p.nextSetBit(c1i + 1);
                if (c2i == -1) {
                    continue;
                }
                for (char c3 = c1; c3 <= c1; c3++) {
                    var c3p = positions.get(c3);
                    if (c3p.isEmpty()) {
                        continue;
                    }
                    int c3i = c3p.nextSetBit(c2i + 1);
                    if (c3i == -1) {
                        continue;
                    }
                    palindromes.add(new Palindrome(c1, c2, c3));
                }
            }
        }
        return palindromes.size();
    }

    private record Palindrome(char c1, char c2, char c3) {
    }

}
