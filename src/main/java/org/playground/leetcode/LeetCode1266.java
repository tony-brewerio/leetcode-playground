package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/minimum-time-visiting-all-points/submissions/1111765445/">Submission</a>
 * <p>
 * This one was easy indeed.
 */
public class LeetCode1266 {
    public int minTimeToVisitAllPoints(int[][] points) {
        int time = 0;
        for (var i = 1; i < points.length; i++) {
            var dx = Math.abs(points[i][0] - points[i - 1][0]);
            var dy = Math.abs(points[i][1] - points[i - 1][1]);
            time += Math.max(dx, dy);
        }
        return time;
    }
}
