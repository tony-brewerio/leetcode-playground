package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/sort-characters-by-frequency/submissions/1169113856/">Submission</a>
 * Runtime: 6 ms, Beats 92.52% of users with Java
 * Memory: 44.50 MB, Beats 91.94% of users with Java ( varies greatly between runs )
 */
public class LeetCode368 {
    private final Logger log = LoggerFactory.getLogger(LeetCode368.class);

    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        // precompute map of divisible numbers going up, use arrays for efficiency
        var divisible = new int[nums.length][8];
        for (int i = 0; i < (nums.length - 1); i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] % nums[i] == 0) {
                    var d = divisible[i];
                    d[++d[0]] = j;
                    if ((d.length - 1) == d[0]) {
                        divisible[i] = Arrays.copyOf(d, (int) (d.length * 1.5 + 8));
                    }
                }
            }
        }
        int[][] holder = new int[1][nums.length + 1];
        int[] best = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            largestDivisibleSubset(best, divisible, holder, nums, i, new int[nums.length + 1]);
        }
        var result = new ArrayList<Integer>();
        for (int i = 1; i <= holder[0][0]; i++) {
            result.add(holder[0][i]);
        }
        return result;
    }

    public void largestDivisibleSubset(int[] best, int[][] divisible, int[][] holder, int[] nums, int i, int[] subset) {
        int length = subset[0] + 1;
        if (best[i] >= length) {
            return;
        } else {
            best[i] = length;
        }
        //
        int num = nums[i];
        subset = Arrays.copyOf(subset, subset.length);
        subset[++subset[0]] = num;
        if (subset[0] > holder[0][0]) {
            holder[0] = subset;
        }
        //
        int[] d = divisible[i];
        for (int j = 1; j <= d[0]; j++) {
            largestDivisibleSubset(best, divisible, holder, nums, d[j], subset);
        }
    }
}
