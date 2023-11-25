package org.playground.leetcode;

import org.playground.leetcode.helpers.AveragingOperationTimer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see <a href="https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/submissions/1106317553/">Submission</a>
 * Runtime: 371 ms, Beats 5.13% of users with Java
 * Memory: 71.91 MB, Beats 7.69% of users with Java
 * <p>
 * Accepted, but slow.
 * Optimized it as much as I could, really, but no help.
 * I have to rething the approach.
 * If anything, perhaps going the other way around would be better, i.e. compiling puzzles and iterating over words.
 */
public class LeetCode1178 {
    private AveragingOperationTimer timer = new AveragingOperationTimer(LeetCode1178.class);

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        var wmap = new ArrayList<Map<Integer, Integer>>(26);
        for (int i = 0; i < 26; i++) {
            wmap.add(new HashMap<>());
        }
        for (var word : words) {
            var wsetAndUniqCount = toIntBitsWithUniqCount(word);
            var wset = wsetAndUniqCount[0];
            var uniq = wsetAndUniqCount[1];
            if (uniq > 7) {
                continue;
            }
            for (int b = 0; b < 26; b++) {
                if ((wset & (1 << b)) != 0) {
                    var curr = wmap.get(b).get(wset);
                    wmap.get(b).put(wset, curr != null ? curr + 1 : 1);
                }
            }
        }
        var result = new ArrayList<Integer>(words.length);
        for (var puzzle : puzzles) {
            int count = 0;
            var pset = toIntBits(puzzle);
            var wlist = wmap.get((char) (puzzle.charAt(0) - 'a'));
            if (wlist != null) {
                for (var e : wlist.entrySet()) {
                    if ((pset & e.getKey()) == e.getKey()) {
                        count += e.getValue();
                    }
                }
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
