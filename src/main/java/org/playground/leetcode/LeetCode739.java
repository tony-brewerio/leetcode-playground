package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @see <a href="https://leetcode.com/problems/daily-temperatures/submissions/1162305758/">Submission</a>
 * Runtime: 212 ms, Beats 5.10% of users with Java
 * Memory: 60.80 MB, Beats 36.14% of users with Java ( varies greatly between runs )
 * <p>
 * Works, but is very slow due to TreeSet.
 */
public class LeetCode739 {
    private final Logger log = LoggerFactory.getLogger(LeetCode739.class);

    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        TreeSet<TempWithIndex> prevSeenTemps = new TreeSet<>();
        for (int i = 0; i < temperatures.length; i++) {
            int temp = temperatures[i];
            var ti = new TempWithIndex(temp, i);
            var iter = prevSeenTemps.headSet(new TempWithIndex(temp, -1)).iterator();
            while (iter.hasNext()) {
                var remove = iter.next();
                result[remove.index] = ti.index - remove.index;
                iter.remove();
            }
            prevSeenTemps.add(ti);
        }
        return result;
    }

    public class TempWithIndex implements Comparable<TempWithIndex> {
        private final int temp;
        private final int index;

        public TempWithIndex(int temp, int index) {
            this.temp = temp;
            this.index = index;
        }

        @Override
        public int compareTo(TempWithIndex o) {
            int comp = Integer.compare(temp, o.temp);
            return comp != 0 ? comp : Integer.compare(index, o.index);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof TempWithIndex o) {
                return temp == o.temp && index == o.index;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(temp + index);
        }
    }
}
