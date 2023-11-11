package org.playground.leetcode;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/**
 * @see <a href="https://leetcode.com/problems/restore-the-array-from-adjacent-pairs/submissions/1096232787/">Submission</a>
 */
public class LeetCode1743 {

    public int[] restoreArray(int[][] adjacentPairs) {
        var map = new HashMap<Integer, ArrayDeque<Integer>>();
        for (int[] pair : adjacentPairs) {
            addNewPair(map, pair[0], pair[1]);
        }
        return map.values().stream().findFirst().orElseThrow().stream().mapToInt(i -> i).toArray();
    }

    public void addNewPair(Map<Integer, ArrayDeque<Integer>> map, int p1, int p2) {
        var deq1 = map.remove(p1);
        var deq2 = map.remove(p2);
        // no deqs to add to, so put a new one in the map
        if (deq1 == null && deq2 == null) {
            addNewDeq(map, p1, p2);
            return;
        }
        // if there is only one deq found, simply append the number to it
        if (deq1 != null && deq2 == null) {
            addToDeq(map, deq1, p1, p2);
            return;
        }
        if (deq1 == null && deq2 != null) {
            addToDeq(map, deq2, p2, p1);
            return;
        }
        // at there are deqs for both numbers, combine them together
        combineTwoDeqs(map, deq1, deq2, p1, p2);
    }

    public void addNewDeq(Map<Integer, ArrayDeque<Integer>> map, int p1, int p2) {
        var deq = new ArrayDeque<Integer>();
        deq.offer(p1);
        deq.offer(p2);
        map.put(p1, deq);
        map.put(p2, deq);
    }

    public void addToDeq(Map<Integer, ArrayDeque<Integer>> map, ArrayDeque<Integer> deq, int p1, int p2) {
        if (deq.getFirst() == p1) {
            deq.offerFirst(p2);
        } else {
            deq.offerLast(p2);
        }
        map.put(p2, deq);
    }

    public void combineTwoDeqs(Map<Integer, ArrayDeque<Integer>> map, ArrayDeque<Integer> deq1, ArrayDeque<Integer> deq2, int p1, int p2) {
        if (deq1.getFirst() == p1 && deq2.getFirst() == p2) {
            // [1,2,3] [4,5,6] 1 4
            if (deq1.size() >= deq2.size()) {
                deq2.forEach(deq1::offerFirst);
                map.put(deq1.getFirst(), deq1);
            } else {
                deq1.forEach(deq2::offerFirst);
                map.put(deq2.getFirst(), deq2);
            }
        } else if (deq1.getFirst() == p1 && deq2.getLast() == p2) {
            // [1,2,3] [4,5,6] 1 6
            if (deq1.size() >= deq2.size()) {
                deq2.descendingIterator().forEachRemaining(deq1::offerFirst);
                map.put(deq1.getFirst(), deq1);
            } else {
                deq1.forEach(deq2::offerLast);
                map.put(deq2.getLast(), deq2);
            }
        } else if (deq1.getLast() == p1 && deq2.getFirst() == p2) {
            // [1,2,3] [4,5,6] 3 4
            if (deq1.size() >= deq2.size()) {
                deq2.forEach(deq1::offerLast);
                map.put(deq1.getLast(), deq1);
            } else {
                deq1.descendingIterator().forEachRemaining(deq2::offerFirst);
                map.put(deq2.getFirst(), deq2);
            }
        } else if (deq1.getLast() == p1 && deq2.getLast() == p2) {
            // [1,2,3] [4,5,6] 3 6
            if (deq1.size() >= deq2.size()) {
                deq2.descendingIterator().forEachRemaining(deq1::offerLast);
                map.put(deq1.getLast(), deq1);
            } else {
                deq1.descendingIterator().forEachRemaining(deq2::offerLast);
                map.put(deq2.getLast(), deq2);
            }
        }
    }

}
