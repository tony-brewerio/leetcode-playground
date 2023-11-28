package org.playground.leetcode;

/**
 * @see <a href="https://leetcode.com/problems/number-of-ways-to-divide-a-long-corridor/submissions/1108408707/">Submission</a>
 * Runtime: 21 ms, Beats 97.30% of users with Java
 * Memory: 44.58 MB, Beats 72.97% of users with Java
 * <p>
 * Relatively simple one, once you get the trick of counting plants between pairs of sofas.
 * Had to use long for the result, since a single multiplication can overflow the int.
 */
public class LeetCode2147 {
    private static final long MODULO = (long) (Math.pow(10, 9) + 7);

    public int numberOfWays(String corridor) {
        int seats = 0;
        int plants = 0;
        long result = 1;
        for (int i = 0; i < corridor.length(); i++) {
            var c = corridor.charAt(i);
            if (c == 'P') {
                if (seats == 2) {
                    plants++;
                }
            } else {
                seats++;
                if (seats == 3) {
                    result = (result * (plants + 1)) % MODULO;
                    seats = 1;
                    plants = 0;
                }
            }
        }
        return (int) (seats == 2 ? result : 0);
    }
}
