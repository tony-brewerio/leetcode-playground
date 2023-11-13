package org.playground.leetcode;

/**
 * @see <a href="https://leetcode.com/problems/sort-vowels-in-a-string/submissions/1097578500/">Submission</a>
 * Runtime: 5 ms, Beats 100.00% of users with Java
 * Memory: 43.52 MB, Beats 100.00% of users with Java
 * <p>
 * Constraints:
 * 1 <= s.length <= 10^5 (100_000)
 * s consists only of letters of the English alphabet in uppercase and lowercase.
 * <p>
 * This one is WAY easier than all others I've done so far, and the optimal solution is immediately obvious.
 * Initially, I've used ArrayList to store vowels, but sorting ArrayList takes like 50 time longer than char[].
 * After checking other solutions, and trying it out, it seems obvious that doing second run over the string is not great.
 * Recording vowel positions and slotting vowels back from them is much faster than another iteration over the whole string.
 * <p>
 * After looking over the recommended approach, updated the code to use counting sort instead.
 * An interesting note is that using boolean array to test chars for vowels is much faster than if tree.
 */
public class LeetCode2785 {

    public String sortVowels(String str) {
        char[] chars = str.toCharArray();
        // The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in lowercase or uppercase.
        boolean[] vowels = new boolean[127];
        vowels['A'] = true;
        vowels['E'] = true;
        vowels['I'] = true;
        vowels['O'] = true;
        vowels['U'] = true;
        vowels['a'] = true;
        vowels['e'] = true;
        vowels['i'] = true;
        vowels['o'] = true;
        vowels['u'] = true;
        int[] counts = new int[127];
        int[] positions = new int[chars.length];
        int vl = 0;
        for (int i = 0; i < chars.length; i++) {
            var c = chars[i];
            if (vowels[c]) {
                counts[c]++;
                positions[vl++] = i;
            }
        }
        if (vl == 0) {
            return str;
        }
        int vi = 0;
        for (var vowel : new char[]{'A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u'}) {
            for (int i = 0; i < counts[vowel]; i++) {
                chars[positions[vi++]] = vowel;
            }
        }
        return new String(chars);
    }

}
