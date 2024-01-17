package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * @see <a href="https://leetcode.com/problems/unique-number-of-occurrences/submissions/1149022397/">Submission</a>
 * Runtime: 2 ms, Beats 98.10% of users with Java
 * Memory: 42.10 MB, Beats 5.39% of users with Java ( varies greatly between runs )
 * <p>
 * Pretty clean, and fast enough.
 */
public class LeetCode1207 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1207.class);

    public boolean uniqueOccurrences(int[] arr) {
        var counts = new HashMap<Integer, Integer>();
        for (int num : arr) {
            counts.merge(num, 1, Integer::sum);
        }
        return new HashSet<>(counts.values()).size() == counts.size();
    }
}
