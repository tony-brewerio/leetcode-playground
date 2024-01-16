package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @see <a href="https://leetcode.com/problems/insert-delete-getrandom-o1/submissions/1148117943/">Submission</a>
 * Runtime: 44 ms, Beats 42.61% of users with Java
 * Memory: 89.08 MB, Beats 85.33% of users with Java ( varies greatly between runs )
 * <p>
 * Custom rolled naive HashMap. Since we have access directly to buckets, it is possible to get random element easily.
 * It is kind of slow though, relatively speaking.
 */
public class LeetCode380 {
    private final Logger log = LoggerFactory.getLogger(LeetCode380.class);

    public static class RandomizedSet {
        private static final Random random = new Random();
        private List<List<Integer>> buckets;
        private int mod;
        private int size;

        public RandomizedSet() {
            mod = 16;
            size = 0;
            buckets = new ArrayList<>();
            for (int i = 0; i < mod; i++) {
                buckets.add(new ArrayList<>(mod));
            }
        }

        private void reallocate() {
            var _buckets = buckets;
            //
            mod = Math.round(mod * 1.4f + 4);
            size = 0;
            buckets = new ArrayList<>();
            for (int i = 0; i < mod; i++) {
                buckets.add(new ArrayList<>(mod));
            }
            //
            for (List<Integer> bucket : _buckets) {
                for (Integer i : bucket) {
                    insert(i);
                }
            }
        }

        public boolean insert(int val) {
            var bucket = buckets.get(Math.abs(val) % mod);
            if (bucket.contains(val)) {
                return false;
            } else {
                bucket.add(val);
                size++;
                if (bucket.size() > mod) {
                    reallocate();
                }
                return true;
            }
        }

        public boolean remove(int val) {
            var bucket = buckets.get(Math.abs(val) % mod);
            if (bucket.remove(Integer.valueOf(val))) {
                size--;
                return true;
            } else {
                return false;
            }
        }

        public int getRandom() {
            int index = random.nextInt(size);
            for (List<Integer> bucket : buckets) {
                if (index < bucket.size()) {
                    return bucket.get(index);
                } else {
                    index -= bucket.size();
                }
            }
            throw new IllegalStateException();
        }

        public boolean contains(int val) {
            var bucket = buckets.get(Math.abs(val) % mod);
            return bucket.contains(val);
        }
    }
}
