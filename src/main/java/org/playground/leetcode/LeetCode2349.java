package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @see <a href="https://leetcode.com/problems/design-a-number-container-system/submissions/1122034489/">Submission</a>
 * Runtime: 70 ms, Beats 71.19% of users with Java
 * Memory: 97.2 MB, Beats 58.47%% of users with Java ( varies greatly between runs )
 * <p>
 * HashMaps for index->number and TreeSets for number->indexes[]
 * Works well enough.
 */
public class LeetCode2349 {
    private final Logger log = LoggerFactory.getLogger(LeetCode2349.class);

    public NumberContainers numberContainers() {
        return new NumberContainers();
    }


    class NumberContainers {
        private final Map<Integer, Integer> numberByIndex = new HashMap<>();
        private final Map<Integer, TreeSet<Integer>> indexesByNumber = new HashMap<>();

        public NumberContainers() {
        }

        public void change(int index, int number) {
            var prev = numberByIndex.put(index, number);
            if (prev != null) {
                indexesByNumber.get(prev).remove(index);
            }
            indexesByNumber.computeIfAbsent(number, n -> new TreeSet<>()).add(index);
        }

        public int find(int number) {
            var indexes = indexesByNumber.get(number);
            return indexes != null && !indexes.isEmpty() ? indexes.first() : -1;
        }
    }
}
