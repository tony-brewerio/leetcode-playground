package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see <a href="https://leetcode.com/problems/difference-between-ones-and-zeros-in-row-and-column/submissions/1119896578/">Submission</a>
 * Runtime: 9 ms, Beats 70.09% of users with Java
 * Memory: 69.7 MB, Beats 98.17% of users with Java ( varies greatly between runs )
 * <p>
 * Leetcode hints that you should only count ones, and infer zero count from one count.
 * This works of course, and I have a solution submitted that does that, but it is slower somehow.
 * My guess is that adding an if condition into a tight loop makes it worse, but this is Java anyway.
 * Decided to leave the faster solution here.
 */
public class LeetCode2482 {
    private final Logger log = LoggerFactory.getLogger(LeetCode2482.class);

    public int[][] onesMinusZeros(int[][] grid) {
        int[][] rowc = new int[2][grid.length];
        int[][] colc = new int[2][grid[0].length];
        for (int rown = 0; rown < grid.length; rown++) {
            int[] row = grid[rown];
            for (int coln = 0; coln < row.length; coln++) {
                int val = row[coln];
                rowc[val][rown]++;
                colc[val][coln]++;
            }
        }
        int[][] result = new int[grid.length][grid[0].length];
        for (int rown = 0; rown < result.length; rown++) {
            int[] row = result[rown];
            for (int coln = 0; coln < row.length; coln++) {
                row[coln] = rowc[1][rown] + colc[1][coln] - rowc[0][rown] - colc[0][coln];
            }
        }
        return result;
    }
}
