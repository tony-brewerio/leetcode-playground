package org.playground.leetcode;

/**
 * @see <a href="https://leetcode.com/problems/transpose-matrix/submissions/1116426251/">Submission</a>
 */
public class LeetCode867 {

    public int[][] transpose(int[][] matrix) {
        int[][] result = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

}
