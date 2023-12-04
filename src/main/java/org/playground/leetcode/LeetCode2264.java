package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/largest-3-same-digit-number-in-string/submissions/1112408688/">Submission</a>
 * <p>
 * Not much to say.
 * Move sliding window over the array and compare elements inside it.
 */
public class LeetCode2264 {
    private final Logger log = LoggerFactory.getLogger(LeetCode2264.class);

    public String largestGoodInteger(String num) {
        String result = "";
        for (int i = 0; i < (num.length() - 2); i++) {
            if (num.charAt(i) == num.charAt(i + 1) && num.charAt(i + 1) == num.charAt(i + 2)) {
                if (result.isEmpty() || result.charAt(0) < num.charAt(i)) {
                    result = num.substring(i, i + 3);
                }
            }
        }
        return result;
    }
}
