package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;

/**
 * @see <a href="https://leetcode.com/problems/evaluate-reverse-polish-notation/submissions/1161273419/">Submission</a>
 * Runtime: 5 ms, Beats 97.05% of users with Java
 * Memory: 44.12 MB, Beats 72.98% of users with Java ( varies greatly between runs )
 */
public class LeetCode150 {
    private final Logger log = LoggerFactory.getLogger(LeetCode150.class);

    public int evalRPN(String[] tokens) {
        var stack = new ArrayDeque<Integer>();
        for (String token : tokens) {
            switch (token) {
                case "+" -> {
                    int b = stack.pollLast();
                    int a = stack.pollLast();
                    stack.offerLast(a + b);
                }
                case "-" -> {
                    int b = stack.pollLast();
                    int a = stack.pollLast();
                    stack.offerLast(a - b);
                }
                case "*" -> {
                    int b = stack.pollLast();
                    int a = stack.pollLast();
                    stack.offerLast(a * b);
                }
                case "/" -> {
                    int b = stack.pollLast();
                    int a = stack.pollLast();
                    stack.offerLast(a / b);
                }
                default -> {
                    stack.offerLast(Integer.parseInt(token));
                }
            }
        }
        return stack.peekLast();
    }
}
