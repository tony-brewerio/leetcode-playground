package org.playground.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/diagonal-traverse-ii/submissions/1104483391/">Submission</a>
 * Runtime: 45 ms, Beats 20.56% of users with Java
 * Memory: 68.63 MB, Beats 66.74% of users with Java
 * <p>
 * Used the hints, but not checked any other solutions for now.
 * Now its plenty fast, but still much slower than what other people have.
 */
public class LeetCode1424 {

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        var tuples = new ArrayList<int[]>();
        for (int y = 0; y < nums.size(); y++) {
            var row = nums.get(y);
            for (int x = 0; x < row.size(); x++) {
                tuples.add(new int[]{x + y, -y, row.get(x)});
            }
        }
        tuples.sort(Comparator.comparingInt((int[] t) -> t[0]).thenComparingInt((int[] t) -> t[1]));
        }
        return tuples.stream().mapToInt(t -> t[2]).toArray();
    }
}
