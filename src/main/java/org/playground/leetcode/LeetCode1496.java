package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

/**
 * @see <a href="https://leetcode.com/problems/path-crossing/submissions/1126537855/">Submission</a>
 * Runtime: 1 ms, Beats 96.48% of users with Java
 * Memory: 42.09 MB, Beats 6.17% of users with Java ( varies greatly between runs )
 * <p>
 * Very slow, relatively. There must be an algorithmic trick solution to this.
 * <p>
 * Apparently, putting records in a hashmap is bad idea, because they are slow.
 * Whatever implementation records use for equals and hashcode is way slower than hand rolled one.
 */
public class LeetCode1496 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1496.class);

    public boolean isPathCrossing(String path) {
        var visited = new HashSet<Point>(path.length());
        Point prev = new Point(0, 0);
        visited.add(prev);
        for (int i = 0; i < path.length(); i++) {
            switch (path.charAt(i)) {
                case 'N':
                    prev = new Point(prev.x, prev.y + 1);
                    break;
                case 'S':
                    prev = new Point(prev.x, prev.y - 1);
                    break;
                case 'E':
                    prev = new Point(prev.x + 1, prev.y);
                    break;
                case 'W':
                    prev = new Point(prev.x - 1, prev.y);
                    break;
            }
            if (!visited.add(prev)) {
                return true;
            }
        }
        return false;
    }

    public static class Point {
        public final int x;
        public final int y;
        private final int hc;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.hc = x + y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point other) {
                return other.x == this.x && other.y == this.y;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return hc;
        }
    }
}
