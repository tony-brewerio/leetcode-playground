package org.playground.leetcode;

/**
 * @see <a href="https://leetcode.com/problems/number-of-1-bits/submissions/1109114512/">Submission</a>
 * Runtime: 0 ms, Beats 100.00% of users with Java ( all of them are likes this )
 * Memory: 39.18 MB, Beats 78.31% of users with Java
 * <p>
 * Very easy, just iterate over bits and that's it.
 */
public class LeetCode191 {
    public int hammingWeight(int n) {
        int weight = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                weight++;
            }
        }
        return weight;
    }
}
