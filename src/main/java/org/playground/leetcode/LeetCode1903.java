package org.playground.leetcode;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/largest-odd-number-in-string/submissions/1114650496/">Submission</a>
 * <p>
 * Pretty simple - iterate over the characters from the end, cut when we see an odd one.
 */
public class LeetCode1903 {

    public String largestOddNumber(String num) {
        for (int i = num.length() - 1; i >= 0; i--) {
            switch (num.charAt(i)) {
                case '1', '3', '5', '7', '9':
                    return i == (num.length() - 1) ? num : num.substring(0, i + 1);
            }
        }
        return "";
    }

}
