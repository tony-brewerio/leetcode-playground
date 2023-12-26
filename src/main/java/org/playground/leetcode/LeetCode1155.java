package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @see <a href="https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/submissions/1128820258/">Submission</a>
 * Runtime: 3 ms, Beats 99.64% of users with Java
 * Memory: 41.62 MB, Beats 27.94% of users with Java ( varies greatly between runs )
 * <p>
 * This was a good one.
 * The main thing was adding more early stop conditions and using arrays instead of hashmap for cache.
 * The latter is more than fine since our target and n are very small numbers.
 */
public class LeetCode1155 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1155.class);
    private static final int MODULO = (int) (Math.pow(10, 9) + 7);

    public int numRollsToTarget(int n, int k, int target) {
        int[][] cache = new int[n + 1][target + 1];
        for (int[] subcache : cache) {
            Arrays.fill(subcache, -1);
        }
        return numRollsToTarget(n, k, target, cache);
    }

    private int numRollsToTarget(int n, int k, int target, int[][] cache) {
        if (n * k < target) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        var subcache = cache[n];
        int cached = subcache[target];
        if (cached != -1) {
            return cached;
        }
        int rolls = 0;
        int maxroll = Math.min(k, target - n + 1); // we must roll other dice, and they all will roll at least one
        for (int roll = 1; roll <= maxroll; roll++) {
            rolls = (rolls + numRollsToTarget(n - 1, k, target - roll, cache)) % MODULO;
        }
        subcache[target] = rolls;
        return rolls;
    }
}
