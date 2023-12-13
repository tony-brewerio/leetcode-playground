package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/special-positions-in-a-binary-matrix/submissions/1119162287/">Submission</a>
 * Runtime: 1 ms, Beats 99.74% of users with Java
 * Memory: 44.07 MB, Beats 39.37% of users with Java
 * <p>
 * A bit over the top probs, but works fine.
 */
public class LeetCode1582 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1582.class);

    public int numSpecial(int[][] mat) {
        int[] rowi = new int[mat.length];
        int[] coli = new int[mat[0].length];
        Arrays.fill(rowi, -1);
        Arrays.fill(coli, -1);
        for (int rown = 0; rown < mat.length; rown++) {
            int[] row = mat[rown];
            for (int coln = 0; coln < row.length; coln++) {
                if (row[coln] == 1) {
                    if (rowi[rown] == -1) {
                        rowi[rown] = coln;
                    } else {
                        rowi[rown] = -2;
                    }
                    if (coli[coln] == -1) {
                        coli[coln] = rown;
                    } else {
                        coli[coln] = -2;
                    }
                }
            }
        }
        int result = 0;
        for (int coln : rowi) {
            if (coln >= 0 && coli[coln] >= 0) {
                result++;
            }
        }
        return result;
    }
}
