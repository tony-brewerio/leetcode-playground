package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see <a href="https://leetcode.com/problems/image-smoother/submissions/1123670959/">Submission</a>
 * Runtime: 6 ms, Beats 80.00% of users with Java
 * Memory: 44.94 MB, Beats 15.00% of users with Java ( varies greatly between runs )
 * <p>
 * Seems straightforward enough.
 */
public class LeetCode661 {
    private final Logger log = LoggerFactory.getLogger(LeetCode661.class);

    public int[][] imageSmoother(int[][] img) {
        int height = img.length;
        int width = img[0].length;
        int[][] result = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int count = 0;
                int total = 0;
                for (int ii = Math.max(i - 1, 0); ii < Math.min(i + 2, height); ii++) {
                    for (int jj = Math.max(j - 1, 0); jj < Math.min(j + 2, width); jj++) {
                        count++;
                        total += img[ii][jj];
                    }
                }
                result[i][j] = total / count;
            }
        }
        return result;
    }
}
