package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @see <a href="https://leetcode.com/problems/daily-temperatures/submissions/1162325782/">Submission</a>
 * Runtime: 38 ms, Beats 80.41% of users with Java
 * Memory: 59.82 MB, Beats 58.61% of users with Java ( varies greatly between runs )
 * <p>
 * Works, but is very slow due to TreeSet.
 * <p>
 * As usual, using raw arrays is the way to go for speed.
 * Though, there are much faster solution, meaning that this one is very suboptimal.
 * Works well enough though.
 */
public class LeetCode739 {
    private final Logger log = LoggerFactory.getLogger(LeetCode739.class);

    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        //
        boolean[] prevSeenTemps = new boolean[101];
        int[][] prevSeenTempsIndexes = new int[101][16 + 1];
        //
        for (int i = 0; i < temperatures.length; i++) {
            int temp = temperatures[i];
            //
            int[] tempIndexes = prevSeenTempsIndexes[temp];
            int tempIndexesNextSize = ++tempIndexes[0];
            tempIndexes[tempIndexesNextSize] = i;
            if (tempIndexesNextSize == (tempIndexes.length - 1)) {
                tempIndexes = Arrays.copyOf(tempIndexes, (int) ((tempIndexes.length + 16) * 1.4));
                prevSeenTempsIndexes[temp] = tempIndexes;
            }
            //
            for (int j = 30; j < temp; j++) {
                if (prevSeenTemps[j]) {
                    int[] prevTempIndexes = prevSeenTempsIndexes[j];
                    for (int k = 1; k <= prevTempIndexes[0]; k++) {
                        int prevTempIndex = prevTempIndexes[k];
                        result[prevTempIndex] = i - prevTempIndex;
                    }
                    prevTempIndexes[0] = 0;
                    prevSeenTemps[j] = false;
                }
            }
            prevSeenTemps[temp] = true;
        }
        return result;
    }
}
