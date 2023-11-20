package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.BitSet;

/**
 * @see <a href="https://leetcode.com/problems/minimum-amount-of-time-to-collect-garbage/submissions/1103021430/">Submission</a>
 * Runtime: 17 ms, Beats 74.67% of users with Java
 * Memory: 62.40 MB, Beats 36.57% of users with Java
 * <p>
 * The description was a bit unclear, but the actual task is not too bad.
 * Got a working solution that has only one actual if condition, explicitly allocates very little.
 * Not sure why memory results are so bad.
 */
public class LeetCode2931 {
    private final Logger log = LoggerFactory.getLogger(LeetCode2931.class);

    public int garbageCollection(String[] garbage, int[] travel) {
        // M=0,P=1,G=2
        var trucks = new int[127];
        trucks['M'] = 0;
        trucks['P'] = 1;
        trucks['G'] = 2;
        // reuse the same counts array so that it is allocated once
        var counts = new int[3];
        // all trucks start at stop 0
        // state[0] = truck position
        // state[1] = truck cost
        // the first step is "unrolled" from the loop to remove an if condition from the loop, not sure if helps, but why not
        var states = new int[3][2];
        {
            // here counts is zero-initialized
            var g = garbage[0];
            for (int j = 0; j < g.length(); j++) {
                counts[trucks[g.charAt(j)]]++;
            }
            for (int t = 0; t <= 2; t++) {
                states[t][1] = counts[t];
            }
        }
        //
        for (int i = 1; i < garbage.length; i++) {
            // here we need to reset counts
            counts[0] = counts[1] = counts[2] = 0;
            var g = garbage[i];
            for (int j = 0; j < g.length(); j++) {
                counts[trucks[g.charAt(j)]]++;
            }
            //
            for (int t = 0; t <= 2; t++) {
                var count = counts[t];
                var state = states[t];
                if (count > 0) {
                    state[1] += count;
                    for (int j = state[0]; j < i; j++) {
                        state[1] += travel[j];
                    }
                    state[0] = i;
                }
            }
        }
        //
        int total = 0;
        for (int t = 0; t <= 2; t++) {
            total += states[t][1];
        }
        return total;
    }
}
