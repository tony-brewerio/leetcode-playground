package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/group-anagrams/submissions/1168032318/">Submission</a>
 * Runtime: 5 ms, Beats 99.55% of users with Java
 * Memory: 47.56 MB, Beats 54.86% of users with Java ( varies greatly between runs )
 */
public class LeetCode49 {
    private final Logger log = LoggerFactory.getLogger(LeetCode49.class);

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<AnagramKey, List<String>> groups = new HashMap<>();
        for (String str : strs) {
            groups.computeIfAbsent(new AnagramKey(str), k -> new ArrayList<>()).add(str);
        }
        return List.copyOf(groups.values());
    }

    public class AnagramKey {
        private final byte[] counts;
        private final int hc;

        public AnagramKey(String str) {
            counts = new byte[26];
            for (int i = 0; i < str.length(); i++) {
                counts[str.charAt(i) - 'a']++;
            }
            hc = Arrays.hashCode(counts);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof AnagramKey o) {
                return Arrays.equals(counts, o.counts);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return hc;
        }
    }
}
