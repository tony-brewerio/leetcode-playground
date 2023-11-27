package org.playground.leetcode;

import org.playground.leetcode.helpers.AveragingOperationTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/knight-dialer/submissions/1107699377/">Submission</a>
 * Runtime: 26 ms, Beats 68.60% of users with Java
 * Memory: 43.42 MB, Beats 57.58% of users with Java
 * <p>
 * The code seems pretty optimal, but the runtime and memory are kind of not good.
 * There is probably a whole another approach to this instead of dfs + cache.
 */
public class LeetCode935 {
    private static final AveragingOperationTimer timer = new AveragingOperationTimer(LeetCode935.class);
    private static final Logger log = LoggerFactory.getLogger(LeetCode935.class);

    private static final int MODULO = (int) (Math.pow(10, 9) + 7);

    public int knightDialer(int n) {
        int[][] cache = new int[10][n];
        for (int i = 0; i <= 9; i++) {
            Arrays.fill(cache[i], -1);
        }
        int moves = 0;
        for (int i = 0; i <= 9; i++) {
            moves = (moves + rec(i, n - 1, cache)) % MODULO;
        }
        return moves;
    }

    private int rec(int pos, int n, int[][] cache) {
        if (n == 0) {
            return 1;
        }
        if (pos == 5) {
            return 0;
        }
        var pcache = cache[pos];
        var cached = pcache[n];
        if (cached != -1) {
            return cached;
        }
        int moves = 0;
        switch (pos) {
            case 0:
                moves = (moves + rec(4, n - 1, cache)) % MODULO;
                moves = (moves + rec(6, n - 1, cache)) % MODULO;
                break;
            case 1:
                moves = (moves + rec(6, n - 1, cache)) % MODULO;
                moves = (moves + rec(8, n - 1, cache)) % MODULO;
                break;
            case 2:
                moves = (moves + rec(7, n - 1, cache)) % MODULO;
                moves = (moves + rec(9, n - 1, cache)) % MODULO;
                break;
            case 3:
                moves = (moves + rec(4, n - 1, cache)) % MODULO;
                moves = (moves + rec(8, n - 1, cache)) % MODULO;
                break;
            case 4:
                moves = (moves + rec(0, n - 1, cache)) % MODULO;
                moves = (moves + rec(3, n - 1, cache)) % MODULO;
                moves = (moves + rec(9, n - 1, cache)) % MODULO;
                break;
            case 6:
                moves = (moves + rec(0, n - 1, cache)) % MODULO;
                moves = (moves + rec(1, n - 1, cache)) % MODULO;
                moves = (moves + rec(7, n - 1, cache)) % MODULO;
                break;
            case 7:
                moves = (moves + rec(2, n - 1, cache)) % MODULO;
                moves = (moves + rec(6, n - 1, cache)) % MODULO;
                break;
            case 8:
                moves = (moves + rec(1, n - 1, cache)) % MODULO;
                moves = (moves + rec(3, n - 1, cache)) % MODULO;
                break;
            case 9:
                moves = (moves + rec(2, n - 1, cache)) % MODULO;
                moves = (moves + rec(4, n - 1, cache)) % MODULO;
                break;
        }
        pcache[n] = moves;
        return moves;
    }
}
