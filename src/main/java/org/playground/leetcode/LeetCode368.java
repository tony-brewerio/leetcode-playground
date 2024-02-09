package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/largest-divisible-subset/submissions/1170940030/">Submission</a>
 * Runtime: 14 ms, Beats 75.52% of users with Java
 * Memory: 43.38 MB, Beats 23.81% of users with Java ( varies greatly between runs )
 */
public class LeetCode368 {
    private final Logger log = LoggerFactory.getLogger(LeetCode368.class);

    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums.length == 1) {
            List<Integer> result = new ArrayList<>();
            result.add(nums[0]);
            return result;
        }
        Arrays.sort(nums);
        // precompute map of divisible numbers going up, use arrays for efficiency
        int maxsize = 1;
        var sizes = new int[nums.length];
        Arrays.fill(sizes, 1);
        var directions = new int[nums.length];
        Arrays.fill(directions, -1);
        for (int i = 0; i < (nums.length - 1); i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] % nums[i] == 0) {
                    int nextsize = sizes[i] + 1;
                    if (sizes[j] < nextsize) {
                        sizes[j] = nextsize;
                        directions[j] = i;
                        if (nextsize > maxsize) {
                            maxsize = nextsize;
                        }
                    }
                }
            }
        }
        for (int i = sizes.length - 1; i >= 0; i--) {
            if (sizes[i] == maxsize) {
                List<Integer> reversed = new ArrayList<>();
                reversed.add(nums[i]);
                int pi = directions[i];
                while (pi >= 0) {
                    reversed.add(nums[pi]);
                    pi = directions[pi];
                }
                List<Integer> result = new ArrayList<>(reversed.size());
                for (int k = reversed.size() - 1; k >= 0; k--) {
                    result.add(reversed.get(k));
                }
                return result;
            }
        }
        return List.of();
    }
}
