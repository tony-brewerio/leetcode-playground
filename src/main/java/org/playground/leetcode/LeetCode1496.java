package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

/**
 * @see <a href="https://leetcode.com/problems/path-crossing/submissions/1126255246/">Submission</a>
 * Runtime: 14 ms, Beats 5.73% of users with Java
 * Memory: 43.34 MB, Beats 5.7% of users with Java ( varies greatly between runs )
 * <p>
 * Very slow, relatively. There must be an algorithmic trick solution to this.
 */
public class LeetCode1496 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1496.class);

    public boolean isPathCrossing(String path) {
        var visited = new HashSet<Point>();
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

    private record Point(int x, int y) {
    }
}
