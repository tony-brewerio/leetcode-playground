package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/group-anagrams/submissions/1168007206/">Submission</a>
 * Runtime: 8 ms, Beats 38.79% of users with Java
 * Memory: 47.75 MB, Beats 35.61% of users with Java ( varies greatly between runs )
 * <p>
 * Works, but is kind of slow-ish.
 */
public class LeetCode49 {
    private final Logger log = LoggerFactory.getLogger(LeetCode49.class);

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> groups = new HashMap<>();
        for (String str : strs) {
            char[] key = str.toCharArray();
            Arrays.sort(key);
            groups.compute(String.valueOf(key), (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }
                v.add(str);
                return v;
            });
        }
        return List.copyOf(groups.values());
    }
}
