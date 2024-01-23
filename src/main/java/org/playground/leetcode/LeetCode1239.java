package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @see <a href="https://leetcode.com/problems/set-mismatch/submissions/1154018204/">Submission</a>
 * Runtime: 2 ms, Beats 97.67% of users with Java
 * Memory: 45.14 MB, Beats 70.30% of users with Java ( varies greatly between runs )
 */
public class LeetCode1239 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1239.class);

    public int maxLength(List<String> arr) {
        List<BitSet> bitSetList = new ArrayList<>();
        for (String s : arr) {
            BitSet bs = new BitSet();
            for (int i = 0; i < s.length(); i++) {
                bs.set(s.charAt(i) - 'a');
            }
            if (s.length() == bs.cardinality()) {
                bitSetList.add(bs);
            }
        }
        int result = 0;
        for (int i = 0; i < bitSetList.size(); i++) {
            int candidate = maxLength(bitSetList, i, bitSetList.get(i));
            if (candidate > result) {
                result = candidate;
            }
        }
        return result;
    }

    private int maxLength(List<BitSet> bitSetList, int from, BitSet current) {
        current.or(bitSetList.get(from));
        int result = current.cardinality();
        for (int i = from + 1; i < bitSetList.size(); i++) {
            BitSet next = bitSetList.get(i);
            if (!next.intersects(current)) {
                int candidate = maxLength(bitSetList, i, (BitSet) current.clone());
                if (candidate > result) {
                    result = candidate;
                }
            }
        }
        return result;
    }
}
