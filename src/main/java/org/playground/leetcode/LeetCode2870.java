package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/minimum-number-of-operations-to-make-array-empty/submissions/1137055296/">Submission</a>
 * Runtime: 5 ms, Beats 100.00% of users with Java
 * Memory: 61.63 MB, Beats 22.31% of users with Java ( varies greatly between runs )
 * <p>
 * This one was a good problem.
 * <p>
 * Homebrew primitive int HashMap speeds everything up again, making the solution very fast overall.
 * This is kind of cheating, but it works.
 */
public class LeetCode2870 {
    private final Logger log = LoggerFactory.getLogger(LeetCode2870.class);

    public int minOperations(int[] nums) {
        var counts = new IntCountingMap(Math.max(16, nums.length / 16));
        for (int num : nums) {
            counts.increment(num);
        }
        int result = 0;
        for (int count : counts.toValuesArray()) {
            if (count == 1) {
                return -1;
            }
            int mod3 = count % 3;
            result += count / 3 + (mod3 > 0 ? 1 : 0);
        }
        return result;
    }

    public static class IntCountingMap {
        private final int mod;
        private final int[][] buckets;

        public IntCountingMap(int mod) {
            this.mod = mod;
            this.buckets = new int[mod][];
        }

        public void increment(int n) {
            var bi = n % mod;
            int[] bucket = buckets[bi];
            if (bucket == null) {
                bucket = new int[8 * 2 + 1];
                bucket[0] = 3;
                bucket[1] = n;
                bucket[2] = 1;
                buckets[bi] = bucket;
            } else {
                for (int i = 1; i < bucket[0]; i += 2) {
                    if (bucket[i] == n) {
                        bucket[i + 1]++;
                        return;
                    }
                }
                if ((bucket[0] + 2) <= bucket.length) {
                    bucket[bucket[0]] = n;
                    bucket[bucket[0] + 1] = 1;
                    bucket[0] += 2;
                } else {
                    var size = (int) (bucket.length * 1.5 + 8);
                    size = (size % 2 == 0) ? size + 1 : size;
                    bucket = Arrays.copyOf(bucket, size);
                    bucket[bucket[0]] = n;
                    bucket[bucket[0] + 1] = 1;
                    bucket[0] += 2;
                    buckets[bi] = bucket;
                }
            }
        }

        public int get(int n) {
            var bi = n % mod;
            int[] bucket = buckets[bi];
            if (bucket != null) {
                for (int i = 1; i < bucket[0]; i += 2) {
                    if (bucket[i] == n) {
                        return bucket[i + 1];
                    }
                }
            }
            return 0;
        }

        public int[] toValuesArray() {
            int size = 0;
            for (int[] bucket : buckets) {
                if (bucket != null) {
                    size += (bucket[0] - 1) / 2;
                }
            }
            int[] values = new int[size];
            int i = 0;
            for (int[] bucket : buckets) {
                if (bucket != null) {
                    for (int bi = 2; bi < bucket[0]; bi += 2) {
                        values[i++] = bucket[bi];
                    }
                }
            }
            return values;
        }
    }
}
