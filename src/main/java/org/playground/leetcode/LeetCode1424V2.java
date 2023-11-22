package org.playground.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/diagonal-traverse-ii/submissions/1104524270/">Submission</a>
 * Runtime: 22 ms, Beats 85.74% of users with Java
 * Memory: 65.8 MB, Beats 77.69% of users with Java
 * <p>
 * Based on editorial BFS.
 * Used ArrayDeque instead of LinkedList, since it is faster.
 * Also pre-allocated results array.
 * Somehow is not as fast as expected still, go figure.
 */
public class LeetCode1424V2 {

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int total = 0;
        for (var row : nums) {
            total += row.size();
        }
        var result = new int[total];
        var ri = 0;
        //
        Deque<int[]> stack = new ArrayDeque<>();
        stack.offerLast(new int[]{0, 0});
        while (!stack.isEmpty()) {
            var task = stack.poll();
            int rown = task[0];
            int coln = task[1];
            var row = nums.get(rown);
            result[ri++] = row.get(coln);
            if (coln == 0 && (rown + 1) < nums.size()) {
                stack.offerLast(new int[]{rown + 1, coln});
            }
            if (coln + 1 < row.size()) {
                stack.offerLast(new int[]{rown, coln + 1});
            }
        }
        return result;
    }

}
