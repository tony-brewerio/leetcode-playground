package org.playground.leetcode;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/submissions/1110980175/">Submission</a>
 * Runtime: 5 ms, Beats 95.43% of users with Java
 * Memory: 43.88 MB, Beats 87.78% of users with Java
 * <p>
 * Simple solution - build char count, iterate over all the chars, stop if counts mismatch.
 * Not sure why 127 wide array is slower than 26 one.
 * <p>
 * Constraints:
 * 1 <= words.length <= 1000
 * 1 <= words[i].length, chars.length <= 100
 * words[i] and chars consist of lowercase English letters.
 */
public class LeetCode1160 {

    public int countCharacters(String[] words, String chars) {
        // since all strings are 100 chars or fewer, we know that max count of one single char is 100
        // therefore, we can store counts in a byte array, and not an int one, and save some space
        var charsCounts = new byte[26];
        for (int i = 0; i < chars.length(); i++) {
            charsCounts[chars.charAt(i) - 'a']++;
        }
        var wordCounts = new byte[26];
        int result = 0;
        outer:
        for (String word : words) {
            Arrays.fill(wordCounts, (byte) 0);
            for (int i = 0; i < word.length(); i++) {
                var wc = word.charAt(i) - 'a';
                if (++wordCounts[wc] > charsCounts[wc]) {
                    continue outer;
                }
            }
            result += word.length();
        }
        return result;
    }

}
