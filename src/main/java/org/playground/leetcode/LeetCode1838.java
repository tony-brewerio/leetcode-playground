package org.playground.leetcode;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/frequency-of-the-most-frequent-element/submissions/1101662140/">Submission</a>
 * <p>
 * Passes the tests technically, but is very slow and also just ugly.
 */
public class LeetCode1838 {

    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        //
        int[] freqs;
        {
            int uniq = 1;
            for (int i = 0; i < (nums.length - 1); i++) {
                if (nums[i] < nums[i + 1]) {
                    uniq++;
                }
            }
            freqs = new int[uniq * 2];
            int fi = 0;
            int n = nums[0];
            int nc = 0;
            for (int num : nums) {
                if (num != n) {
                    freqs[fi] = n;
                    freqs[fi + 1] = nc;
                    fi += 2;
                    n = num;
                    nc = 1;
                } else {
                    nc++;
                }
            }
            freqs[fi] = n;
            freqs[fi + 1] = nc;
        }
        int max = Integer.MIN_VALUE;
        for (int i = (freqs.length - 2); i >= 0; i -= 2) {
            int budget = k;
            int target = freqs[i];
            int count = freqs[i + 1];
            for (int j = i - 2; j >= 0; j -= 2) {
                int candidate = freqs[j];
                int cost = target - candidate;
                int capability = budget / cost;
                int available = freqs[j + 1];
                if (capability >= available) {
                    budget -= cost * available;
                    count += available;
                    continue;
                }
                count += capability;
                break;
            }
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    public int maxFrequencySimple(int[] nums, int k) {
        Arrays.sort(nums);
        int max = Integer.MIN_VALUE;
        for (int i = (nums.length - 1); i >= 0; i--) {
            int budget = k;
            int target = nums[i];
            int count = 1;
            if (i < (nums.length - 1)) {
                int prev = nums[i + 1];
                if (prev == target) {
                    continue;
                }
            }
            for (int j = i - 1; j >= 0; j--) {
                budget -= target - nums[j];
                if (budget >= 0) {
                    count++;
                } else {
                    break;
                }
            }
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

}
