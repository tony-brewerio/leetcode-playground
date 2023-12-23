package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

/**
 * @see <a href="https://leetcode.com/problems/count-and-say/submissions/1126888129/">Submission</a>
 * Runtime: 2 ms, Beats 90.38% of users with Java
 * Memory: 41.12 MB, Beats 42.01% of users with Java ( varies greatly between runs )
 */
public class LeetCode38 {
    private final Logger log = LoggerFactory.getLogger(LeetCode38.class);

    public String countAndSay(int n) {
        String result = "1";
        for (int i = 1; i < n; i++) {
            result = countAndSay(result);
        }
        return result;
    }

    private String countAndSay(String s) {
        var sb = new StringBuilder();
        char prev = ' ';
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != prev) {
                if (prev != ' ') {
                    sb.append(count);
                    sb.append(prev);
                }
                prev = c;
                count = 1;
            } else {
                count++;
            }
        }
        sb.append(count);
        sb.append(prev);
        return sb.toString();
    }
}
