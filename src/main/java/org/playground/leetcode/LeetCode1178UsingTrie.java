package org.playground.leetcode;

import org.playground.leetcode.helpers.AveragingOperationTimer;

import java.util.ArrayList;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/submissions/1106396496/">Submission</a>
 * Runtime: 70 ms, Beats 15.38% of users with Java
 * Memory: 67.96 MB, Beats 100.00% of users with Java
 * <p>
 * Very naive trie solution.
 * Actually passed the tests, but is kind of slow.
 * Should be obvious, considering how many operations it has to do agains a hash map.
 * Somewhat more memory efficient though, maybe I chose bucket size too big?
 */
public class LeetCode1178UsingTrie {
    private AveragingOperationTimer timer = new AveragingOperationTimer(LeetCode1178UsingTrie.class);

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        var trie = new Trie();
        for (String word : words) {
            var wset = toIntBits(word);
            trie.increment(wset, 0);
        }
        var result = new ArrayList<Integer>(words.length);
        for (var puzzle : puzzles) {
            int count = 0;
            var pset = toIntBits(puzzle);
            int submask = pset;
            int first = 1 << (puzzle.charAt(0) - 'a');
            while (submask > 0) {
                if ((submask & first) != 0) {
                    count += trie.get(submask, 0);
                }
                submask = (submask - 1) & pset;
            }
            result.add(count);
        }
        return result;
    }

    public static class Trie {
        private int count = 0;
        private Trie[] children = new Trie[26];

        public void increment(int n, int pos) {
            int next = nextSetBit(n, pos);
            if (next == -1) {
                count++;
            } else {
                var child = children[next];
                if (child == null) {
                    child = children[next] = new Trie();
                }
                child.increment(n, next + 1);
            }
        }

        public int get(int n, int pos) {
            int next = nextSetBit(n, pos);
            if (next == -1) {
                return count;
            } else {
                var child = children[next];
                if (child != null) {
                    return child.get(n, next + 1);
                }
            }
            return 0;
        }

        private int nextSetBit(int num, int from) {
            for (int i = from; i < 26; i++) {
                if ((num & (1 << i)) != 0) {
                    return i;
                }
            }
            return -1;
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
