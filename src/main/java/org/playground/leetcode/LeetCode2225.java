package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @see <a href="https://leetcode.com/problems/find-players-with-zero-or-one-losses/submissions/1147199570/">Submission</a>
 * Runtime: 76 ms, Beats 76.97% of users with Java
 * Memory: 87.84 MB, Beats 84.64% of users with Java ( varies greatly between runs )
 * <p>
 * Fast enough.
 * With known range of player ids of 10^5, a primitive array can be used to store loss counts, which will definitely be faster.
 */
public class LeetCode2225 {
    private final Logger log = LoggerFactory.getLogger(LeetCode2225.class);

    public List<List<Integer>> findWinners(int[][] matches) {
        var losses = new HashMap<Integer, Integer>();
        for (int[] match : matches) {
            losses.putIfAbsent(match[0], 0);
            losses.merge(match[1], 1, Integer::sum);
        }
        var lossesCountZero = new ArrayList<Integer>();
        var lossesCountOne = new ArrayList<Integer>();
        for (var e : losses.entrySet()) {
            switch (e.getValue()) {
                case 0:
                    lossesCountZero.add(e.getKey());
                    break;
                case 1:
                    lossesCountOne.add(e.getKey());
                    break;
            }
        }
        lossesCountZero.sort(Comparator.comparingInt(Integer::intValue));
        lossesCountOne.sort(Comparator.comparingInt(Integer::intValue));
        return List.of(lossesCountZero, lossesCountOne);
    }
}
