package org.playground.leetcode;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/sort-vowels-in-a-string/submissions/1097571332/">Submission</a>
 * Runtime: 11 ms, Beats 99.28% of users with Java
 * Memory: 44.92 MB, Beats 76.32% of users with Java
 * <p>
 * Constraints:
 * 1 <= s.length <= 10^5 (100_000)
 * s consists only of letters of the English alphabet in uppercase and lowercase.
 * <p>
 * This one is WAY easier than all others I've done so far, and the optimal solution is immediately obvious.
 * Initially, I've used ArrayList to store vowels, but sorting ArrayList takes like 50 time longer than char[].
 * After checking other solutions, and trying it out, it seems obvious that doing second run over the string is not great.
 * Recording vowel positions and slotting vowels back from them is much faster than another iteration over the whole string.
 * The end solution is very fast, but relatively not memory efficient, since arrays are pre-allocated greedily.
 */
public class LeetCode2785 {

    public String sortVowels(String str) {
        char[] chars = str.toCharArray();
        char[] vowels = new char[chars.length];
        int[] positions = new int[chars.length];
        int vl = 0;
        for (int i = 0; i < chars.length; i++) {
            var c = chars[i];
            if (isAsciiVowel(c)) {
                vowels[vl] = c;
                positions[vl] = i;
                vl++;
            }
        }
        if (vl == 0) {
            return str;
        }
        vowels = Arrays.copyOf(vowels, vl);
        positions = Arrays.copyOf(positions, vl);
        Arrays.sort(vowels);
        for (int i = 0; i < vl; i++) {
            chars[positions[i]] = vowels[i];
        }
        return String.valueOf(chars);
    }

    private static boolean isAsciiVowel(char c) {
        // The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in lowercase or uppercase.
        return c == 65 // A
                || c == 69 // E
                || c == 73 // I
                || c == 79 // O
                || c == 85 // U
                || c == 97 // a
                || c == 101 // e
                || c == 105 // i
                || c == 111 // o
                || c == 117; // u
    }

}
