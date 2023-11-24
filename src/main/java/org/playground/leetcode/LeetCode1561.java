package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/maximum-number-of-coins-you-can-get/submissions/1105676816/">Submission</a>
 * Runtime: 27 ms, Beats 98.94% of users with Java
 * Memory: 54.71 MB, Beats 58.13% of users with Java
 * <p>
 * Pretty fast and simple.
 * Sorting input array though, which is idk.
 */
public class LeetCode1561 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1561.class);

    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int coins = 0;
        for (int i = (piles.length - 2); i >= (piles.length / 3); i -= 2) {
            coins += piles[i];
        }
        return coins;
    }
}
