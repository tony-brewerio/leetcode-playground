package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/jump-game-ii/submissions/1128779896/">Submission</a>
 * Runtime: 1 ms, Beats 99.27% of users with Java
 * Memory: 45.41 MB, Beats 6.72% of users with Java ( varies greatly between runs )
 */
public class LeetCode45 {
    private final Logger log = LoggerFactory.getLogger(LeetCode45.class);

    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int li = nums.length - 1;
        int pos = 0;
        int jumps = 0;
        while (true) {
            int j = nums[pos];
            if (pos + j >= li) {
                return jumps + 1;
            }
            //
            int besti = -1;
            int bestdist = -1;
            for (int i = 1; i <= j; i++) {
                int dist = i + nums[pos + i];
                if (dist > bestdist) {
                    bestdist = dist;
                    besti = i;
                }
            }
            pos += besti;
            jumps++;
        }
    }
}
