package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.BitSet;

/**
 * @see <a href="https://leetcode.com/problems/minimum-amount-of-time-to-collect-garbage/submissions/1103021430/">Submission</a>
 * Runtime: 18 ms, Beats 72.57% of users with Java
 * Memory: 70.18 MB, Beats 8.76% of users with Java
 * <p>
 * The description was a bit unclear, but the actual task is not too bad.
 * Is it worth prebuilding anything though? Feels like it is not really, but the memory was very bad relatively.
 * So perhaps most don't do this?
 */
public class LeetCode2931 {
    private final Logger log = LoggerFactory.getLogger(LeetCode2931.class);

    public int garbageCollection(String[] garbage, int[] travel) {
        // M=0,P=1,G=2
        var trucks = new int[127];
        trucks['M'] = 0;
        trucks['P'] = 1;
        trucks['G'] = 2;
        // amount of garbage per type per stop
        var counts = new int[3][garbage.length];
        //
        for (int i = 0; i < garbage.length; i++) {
            var g = garbage[i];
            for (int j = 0; j < g.length(); j++) {
                counts[trucks[g.charAt(j)]][i]++;
            }
        }
        // t = truck
        int total = 0;
        for (int t = 0; t <= 2; t++) {
            int time = counts[t][0];
            int pos = 0;
            for (int i = 1; i < garbage.length; i++) {
                var count = counts[t][i];
                if (count > 0) {
                    time += count;
                    for (int j = pos; j < i; j++) {
                        time += travel[j];
                    }
                    pos = i;
                }
            }
            total += time;
        }
        return total;
    }
}
