package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * @see <a href="https://leetcode.com/problems/unique-number-of-occurrences/submissions/1149031911/">Submission</a>
 * Runtime: 1 ms, Beats 99.44% of users with Java
 * Memory: 42.52 MB, Beats 5.39% of users with Java ( varies greatly between runs )
 * <p>
 * Pretty clean, and fast enough.
 * <p>
 * Updated the solution to use fixed size raw arrays that match input constraints.
 * Is faster that naive usages of hashmaps.
 */
public class LeetCode1207 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1207.class);

    public boolean uniqueOccurrences(int[] arr) {
        var counts = new int[2001];
        for (int num : arr) {
            counts[num + 1000]++;
        }
        var cache = new boolean[2001];
        for (int count : counts) {
            if (count > 0) {
                if (cache[count]) {
                    return false;
                }
                cache[count] = true;
            }
        }
        return true;
    }
}
