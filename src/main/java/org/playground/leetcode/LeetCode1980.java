package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.BitSet;

/**
 * @see <a href="https://leetcode.com/problems/find-unique-binary-string/submissions/1100384361/">Submission</a>
 * Runtime: 3 ms, Beats 32.55% of users with Java
 * Memory: 40.56 MB, Beats 50.73% of users with Java
 * <p>
 * There exists a much much faster solution based on set theory, but I've used recursive one, recommended in hints.
 */
public class LeetCode1980 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1980.class);

    public String findDifferentBinaryString(String[] nums) {
        BitSet[] bits = new BitSet[nums.length];
        for (int i = 0; i < nums.length; i++) {
            var bs = bits[i] = new BitSet(nums.length);
            var s = nums[i];
            for (int j = 0; j < s.length(); j++) {
                bs.set(j, s.charAt(j) == '1');
            }
        }
        BitSet[] solution = new BitSet[1];
        rec(bits, 0, new BitSet(nums.length), solution);
        var sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            sb.append(solution[0].get(i) ? '1' : '0');
        }
        return sb.toString();
    }

    public void rec(BitSet[] bits, int i, BitSet prev, BitSet[] solution) {
        if (i == bits.length) {
            boolean appears = false;
            for (BitSet bs : bits) {
                if (bs.equals(prev)) {
                    appears = true;
                    break;
                }
            }
            if (!appears) {
                solution[0] = prev;
            }
            return;
        }
        if (solution[0] != null) {
            return;
        }
        var hasOne = false;
        var hasZero = false;
        for (BitSet bs : bits) {
            boolean bit = bs.get(i);
            hasOne = hasOne || bit;
            hasZero = hasZero || !bit;
        }
        if (hasOne) {
            var next = (BitSet) prev.clone();
            next.set(i, false);
            rec(bits, i + 1, next, solution);
        }
        if (hasZero) {
            var next = (BitSet) prev.clone();
            next.set(i, true);
            rec(bits, i + 1, next, solution);
        }
    }
}
