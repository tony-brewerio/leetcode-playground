package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see <a href="https://leetcode.com/problems/calculate-money-in-leetcode-bank/submissions/1113880448/">Submission</a>
 * <p>
 * It works. Gauss summation.
 */
public class LeetCode1716 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1716.class);

    public int totalMoney(int n) {
        int weekMoneyBase = 1 + 2 + 3 + 4 + 5 + 6 + 7;
        int weekMoneyIncrement = 7;
        int weeks = n / 7;
        int money = weekMoneyBase * weeks + weekMoneyIncrement * ((weeks * (weeks - 1)) / 2);
        for (int i = 1; i <= n % 7; i++) {
            money += weeks + i;
        }
        return money;
    }
}
