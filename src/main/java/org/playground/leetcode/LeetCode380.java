package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @see <a href="https://leetcode.com/problems/insert-delete-getrandom-o1/submissions/1148136686/">Submission</a>
 * Runtime: 24 ms, Beats 74.04% of users with Java
 * Memory: 91.88 MB, Beats 37.76% of users with Java ( varies greatly between runs )
 * <p>
 * Custom rolled naive HashMap. Since we have access directly to buckets, it is possible to get random element easily.
 * It is kind of slow though, relatively speaking.
 * <p>
 * Used primitive arrays for custom HashMap, works much faster.
 * The proper way to do this, probably, is to have a regular HashMap, and then a separate indexed collection for random.
 * Not sure which collection though, since removing items is O(N) in pretty much every collection I can think of.
 * I guess what could be done is leave around tombstones, and reallocate indexed array only if too many items removed.
 */
public class LeetCode380 {
    private final Logger log = LoggerFactory.getLogger(LeetCode380.class);

    public static class RandomizedSet {

        private static final Random random = new Random();
        private int[][] buckets;
        private int mod;
        private int size;

        public RandomizedSet() {
            mod = 16;
            size = 0;
            buckets = new int[mod][16 + 1];
        }

        private void reallocate() {
            var _buckets = buckets;
            //
            mod = Math.round(mod * 1.4f + 4);
            size = 0;
            buckets = new int[mod][16 + 1];
            //
            for (var bucket : _buckets) {
                for (int i = 1; i <= bucket[0]; i++) {
                    insert(bucket[i]);
                }
            }
        }

        public boolean insert(int val) {
            var bucket = buckets[Math.abs(val) % mod];
            boolean contains = false;
            for (int i = 1; i <= bucket[0]; i++) {
                if (bucket[i] == val) {
                    contains = true;
                    break;
                }
            }
            if (contains) {
                return false;
            } else {
                bucket[++bucket[0]] = val;
                size++;
                if (bucket[0] == (bucket.length - 1)) {
                    if (bucket.length > mod) {
                        reallocate();
                    } else {
                        buckets[Math.abs(val) % mod] = Arrays.copyOf(bucket, Math.round(bucket.length * 1.4f + 4));
                    }
                }
                return true;
            }
        }

        public boolean remove(int val) {
            var bucket = buckets[Math.abs(val) % mod];
            for (int i = 1; i <= bucket[0]; i++) {
                if (bucket[i] == val) {
                    for (int j = i + 1; j <= bucket[0]; j++) {
                        bucket[j - 1] = bucket[j];
                    }
                    bucket[0]--;
                    size--;
                    return true;
                }
            }
            return false;
        }

        public int getRandom() {
            int index = random.nextInt(size);
            for (var bucket : buckets) {
                if (index < bucket[0]) {
                    return bucket[index + 1];
                } else {
                    index -= bucket[0];
                }
            }
            throw new IllegalStateException();
        }

        public boolean contains(int val) {
            var bucket = buckets[Math.abs(val) % mod];
            for (int i = 1; i <= bucket[0]; i++) {
                if (bucket[i] == val) {
                    return true;
                }
            }
            return false;
        }
    }
}
