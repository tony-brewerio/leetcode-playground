package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/minimum-falling-path-sum/submissions/1151029675/">Submission</a>
 * Runtime: 4 ms, Beats 75.96% of users with Java
 * Memory: 45.50 MB, Beats 20.02% of users with Java ( varies greatly between runs )
 */
public class LeetCode931 {
    private final Logger log = LoggerFactory.getLogger(LeetCode931.class);

    public int minFallingPathSum(int[][] matrix) {
        int matrixSize = matrix.length;
        int matrixSizeMinusOne = matrix.length - 1;
        int[][] cache = new int[matrixSize][matrixSize];
        // fill the cache with arbitrary very large value
        for (int i = 0; i < matrixSize; i++) {
            Arrays.fill(cache[i], Integer.MAX_VALUE);
        }
        // first line of cache is the same as first line of the matrix
        System.arraycopy(matrix[0], 0, cache[0], 0, matrixSize);
        // go over all the lines, and pick the smallest value from the up to fall into the current cell
        for (int rown = 1; rown < matrixSize; rown++) {
            int[] prevCacheRow = cache[rown - 1];
            int[] currCacheRow = cache[rown];
            int[] currMatrixRow = matrix[rown];
            for (int i = 0; i < matrixSize; i++) {
                int currMatrixValue = currMatrixRow[i];
                int prevRowValue = prevCacheRow[i];
                if (i > 0) {
                    int candidate = prevCacheRow[i - 1];
                    if (candidate < prevRowValue) {
                        prevRowValue = candidate;
                    }
                }
                if (i < matrixSizeMinusOne) {
                    int candidate = prevCacheRow[i + 1];
                    if (candidate < prevRowValue) {
                        prevRowValue = candidate;
                    }
                }
                currCacheRow[i] = currMatrixValue + prevRowValue;
            }
        }
        //
        int result = Integer.MAX_VALUE;
        int[] lastCacheRow = cache[matrixSizeMinusOne];
        for (int i = 0; i < matrixSize; i++) {
            int candidate = lastCacheRow[i];
            if (candidate < result) {
                result = candidate;
            }
        }
        return result;
    }
}
