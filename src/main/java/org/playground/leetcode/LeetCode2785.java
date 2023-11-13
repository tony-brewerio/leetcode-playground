package org.playground.leetcode;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/sort-vowels-in-a-string/submissions/1097561380/">Submission</a>
 * Runtime: 14 ms, Beats 98.68% of users with Java
 * Memory: 44.66 MB, Beats 87.14% of users with Java
 * <p>
 * Constraints:
 * 1 <= s.length <= 10^5 (100_000)
 * s consists only of letters of the English alphabet in uppercase and lowercase.
 * <p>
 * This one is WAY easier than all others I've done so far, and the optimal solution is immediately obvious.
 * Initially, I've used ArrayList to store vowels, but sorting ArrayList takes like 50 time longer than char[].
 * The end solution is very fast and uses very little memory.
 */
public class LeetCode2785 {

    public String sortVowels(String str) {
        char[] chars = str.toCharArray();
        char[] vowels = new char[chars.length];
        int vl = 0;
        for (var c : chars) {
            if (isAsciiVowel(c)) {
                vowels[vl] = c;
                vl++;
            }
        }
        if (vl == 0) {
            return str;
        }
        vowels = Arrays.copyOf(vowels, vl);
        Arrays.sort(vowels);
        int vi = 0;
        for (int i = 0; i < chars.length; i++) {
            if (isAsciiVowel(chars[i])) {
                chars[i] = vowels[vi];
                vi++;
            }
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
