package org.playground.leetcode;

import org.playground.leetcode.helpers.AveragingOperationTimer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/submissions/1106341981/">Submission</a>
 * Runtime: 58 ms, Beats 56.41% of users with Java
 * Memory: 71.75 MB, Beats 15.38% of users with Java
 * <p>
 * After googling around, I found how it iterate over bitsets.
 * This can replace & tests over each and every word with a simple hashmap lookup for each subset.
 * Since puzzles are small, they will have very limited amount of subsets, so it is a lot less operations overall.
 * The thing is still slow somehow, what else to do?
 */
public class LeetCode1178 {
    private AveragingOperationTimer timer = new AveragingOperationTimer(LeetCode1178.class);

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        var wmap = new HashMap<Integer, Integer>(1 << 8);
        for (var word : words) {
            var wsetAndUniqCount = toIntBitsWithUniqCount(word);
            var wset = wsetAndUniqCount[0];
            var uniq = wsetAndUniqCount[1];
            if (uniq > 7) {
                continue;
            }
            wmap.put(wset, wmap.getOrDefault(wset, 0) + 1);
        }
        var result = new ArrayList<Integer>(words.length);
        for (var puzzle : puzzles) {
            int count = 0;
            var pset = toIntBits(puzzle);
            int submask = pset;
            int first = 1 << (puzzle.charAt(0) - 'a');
            while (submask > 0) {
                if ((submask & first) != 0) {
                    count += wmap.getOrDefault(submask, 0);
                }
                submask = (submask - 1) & pset;
            }
            result.add(count);
        }
        return result;
    }

    private int[] toIntBitsWithUniqCount(String s) {
        var set = 0;
        var uniq = 0;
        for (int i = 0; i < s.length(); i++) {
            var b = (1 << (s.charAt(i) - 'a'));
            if ((set & b) == 0) {
                set = set | b;
                uniq++;
            }
        }
        return new int[]{set, uniq};
    }

    private int toIntBits(String s) {
        var set = 0;
        for (int i = 0; i < s.length(); i++) {
            set = set | (1 << (s.charAt(i) - 'a'));
        }
        return set;
    }
}
