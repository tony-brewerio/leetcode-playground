package org.playground.leetcode;

import org.playground.leetcode.helpers.AveragingOperationTimer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/submissions/1106380606/">Submission</a>
 * Runtime: 34 ms, Beats 100.00% of users with Java
 * Memory: 69.92 MB, Beats 94.87% of users with Java
 * <p>
 * After googling around, I found how it iterate over bitsets.
 * This can replace & tests over each and every word with a simple hashmap lookup for each subset.
 * Since puzzles are small, they will have very limited amount of subsets, so it is a lot less operations overall.
 * <p>
 * Went a bit too far by creating a specialized counting map using primitive arrays.
 * The solution is now very fast, but this can be called cheating perhaps.
 */
public class LeetCode1178 {
    private AveragingOperationTimer timer = new AveragingOperationTimer(LeetCode1178.class);

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        var wmap = new IntCountingMap(Math.max(words.length / 8, 8));
        for (String word : words) {
            var wset = toIntBits(word);
            wmap.increment(wset);
        }
        var result = new ArrayList<Integer>(words.length);
        for (var puzzle : puzzles) {
            int count = 0;
            var pset = toIntBits(puzzle);
            int submask = pset;
            int first = 1 << (puzzle.charAt(0) - 'a');
            while (submask > 0) {
                if ((submask & first) != 0) {
                    count += wmap.get(submask);
                }
                submask = (submask - 1) & pset;
            }
            result.add(count);
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
    }

    private int toIntBits(String s) {
        var set = 0;
        for (int i = 0; i < s.length(); i++) {
            set = set | (1 << (s.charAt(i) - 'a'));
        }
        return set;
    }
}
