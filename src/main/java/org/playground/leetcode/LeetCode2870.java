package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @see <a href="https://leetcode.com/problems/minimum-number-of-operations-to-make-array-empty/submissions/1137039645/">Submission</a>
 * Runtime: 18 ms, Beats 86.15% of users with Java
 * Memory: 58.39 MB, Beats 32.31% of users with Java ( varies greatly between runs )
 * <p>
 * This one was a good problem.
 */
public class LeetCode2870 {
    private final Logger log = LoggerFactory.getLogger(LeetCode2870.class);

    public int minOperations(int[] nums) {
        var map = new HashMap<Integer, Integer>();
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        int result = 0;
        for (var count : map.values()) {
            switch (count) {
                case 1:
                    return -1;
                case 2, 3:
                    result += 1;
                    break;
                default:
                    switch (count % 3) {
                        case 0:
                            result += count / 3;
                            break;
                        default:
                            result += (count / 3) + 1;
                            break;
                    }
            }
        }
        return result;
    }
}
