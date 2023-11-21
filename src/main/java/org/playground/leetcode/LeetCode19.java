package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/remove-nth-node-from-end-of-list/submissions/1103065006/">Submission</a>
 * <p>
 * Note that runtime stats are useless, since lists are really really small.
 * Also, this solution mutates the original list, which may or may not be allowed.
 * Leetcode does not care, but perhaps should.
 */
public class LeetCode19 {
    private final Logger log = LoggerFactory.getLogger(LeetCode19.class);

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pnth = head;
        ListNode nth = head;
        int k = n;
        for (var h = head; h != null; h = h.next) {
            if (k < 0) {
                pnth = pnth.next;
            }
            if (k <= 0) {
                nth = nth.next;
            }
            k--;
        }
        if (nth == head) {
            return head.next;
        } else {
            pnth.next = nth.next;
            return head;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public int[] toArray() {
            var list = new ArrayList<Integer>();
            for (var node = this; node != null; node = node.next) {
                list.add(node.val);
            }
            return list.stream().mapToInt(i -> i).toArray();
        }

        public static ListNode of(int... list) {
            ListNode tail = new ListNode(list[list.length - 1]);
            for (int i = list.length - 2; i >= 0; i--) {
                tail = new ListNode(list[i], tail);
            }
            return tail;
        }
    }
}
