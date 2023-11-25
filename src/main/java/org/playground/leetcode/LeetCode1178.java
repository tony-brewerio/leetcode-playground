package org.playground.leetcode;

import java.util.*;

/**
 * WIP
 * Works for smaller test cases, but times out on bigger ones.
 */
public class LeetCode1178 {

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        var wmap = new HashMap<Character, List<Set<Character>>>();
        for (var word : words) {
            var wset = toCharSet(word);
            for (Character wc : wset) {
                wmap.computeIfAbsent(wc, c -> new ArrayList<>()).add(wset);
            }
        }
        var result = new ArrayList<Integer>(words.length);
        for (var puzzle : puzzles) {
            int count = 0;
            var pset = toCharSet(puzzle);
            var wlist = wmap.get(puzzle.charAt(0));
            if (wlist != null) {
                for (var wset : wlist) {
                    if (pset.containsAll(wset)) {
                        count++;
                    }
                }
            }
            result.add(count);
        }
        return result;
    }

    private Set<Character> toCharSet(String s) {
        var pset = new HashSet<Character>(s.length());
        for (int i = 0; i < s.length(); i++) {
            pset.add(s.charAt(i));
        }
        return pset;
    }

}
