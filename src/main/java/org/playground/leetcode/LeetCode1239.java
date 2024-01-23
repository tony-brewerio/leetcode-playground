package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @see <a href="https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/submissions/1154940865/">Submission</a>
 * Runtime: 2 ms, Beats 98.99% of users with Java
 * Memory: 40.59 MB, Beats 96.67% of users with Java ( varies greatly between runs )
 * <p>
 * Overdone manual bit manipulation version, not HashSet no BitSet.
 * Runs plenty fast though.
 */
public class LeetCode1239 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1239.class);

    public int maxLength(List<String> arr) {
        List<Integer> bitMaskList = new ArrayList<>();
        outer:
        for (String s : arr) {
            int bm = 0;
            boolean[] seen = new boolean[26];
            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i) - 'a';
                if (seen[c]) {
                    continue outer;
                }
                seen[c] = true;
                bm = bm | (1 << c);
            }
            bitMaskList.add(bm);
            bitMaskList.add(s.length());
        }
        int[] bitMaskArray = new int[bitMaskList.size()];
        for (int i = 0; i < bitMaskList.size(); i++) {
            bitMaskArray[i] = bitMaskList.get(i);
        }
        int result = 0;
        for (int i = 0; i < bitMaskArray.length; i += 2) {
            int candidate = maxLength(bitMaskArray, i, bitMaskArray[i], 0);
            if (candidate > result) {
                result = candidate;
            }
        }
        return result;
    }

    private int maxLength(int[] bitMaskArray, int from, int prevBitMask, int prevLength) {
        int currBitMask = prevBitMask | bitMaskArray[from];
        int currLength = prevLength + bitMaskArray[from + 1];
        int result = currLength;
        for (int i = from + 2; i < bitMaskArray.length; i += 2) {
            int nextBitMask = bitMaskArray[i];
            if ((nextBitMask & currBitMask) == 0) {
                int candidate = maxLength(bitMaskArray, i, currBitMask, currLength);
                if (candidate > result) {
                    result = candidate;
                }
            }
        }
        return result;
    }
}
