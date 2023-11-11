package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1743Test {

    @MethodSource("argumentsForTestRestoreArray")
    @ParameterizedTest
    void testRestoreArray(int[][] pairs, int[] expected) {
        int[] restored = new LeetCode1743().restoreArray(pairs);
        assertThat(restored).isEqualTo(expected);
    }

    @MethodSource("argumentsForTestCombineTwoDeqs")
    @ParameterizedTest
    void testCombineTwoDeqs(ArrayDeque<Integer> deq1, ArrayDeque<Integer> deq2, int p1, int p2, List<Integer> expectedKeys, ArrayDeque<Integer> expectedDeq) {
        var map = new HashMap<Integer, ArrayDeque<Integer>>();
        map.put(deq1.getFirst(), deq1);
        map.put(deq1.getLast(), deq1);
        map.put(deq2.getFirst(), deq2);
        map.put(deq2.getLast(), deq2);
        map.remove(p1);
        map.remove(p2);
        new LeetCode1743().combineTwoDeqs(map, deq1, deq2, p1, p2);
        assertThat(map).usingRecursiveComparison().isEqualTo(expectedKeys.stream().collect(Collectors.toMap(k -> k, k -> expectedDeq)));
    }

    private static Stream<Arguments> argumentsForTestCombineTwoDeqs() {
        var arguments = new ArrayList<Arguments>();
        // first - first
        arguments.add(Arguments.of(
                intDeqOf(1, 2, 3, 4), intDeqOf(5, 6, 7), 1, 5,
                List.of(4, 7), intDeqOf(7, 6, 5, 1, 2, 3, 4)
        ));
        arguments.add(Arguments.of(
                intDeqOf(1, 2, 3), intDeqOf(5, 6, 7, 8), 1, 5,
                List.of(3, 8), intDeqOf(3, 2, 1, 5, 6, 7, 8)
        ));
        // first - last
        arguments.add(Arguments.of(
                intDeqOf(1, 2, 3, 4), intDeqOf(5, 6, 7), 1, 7,
                List.of(4, 5), intDeqOf(5, 6, 7, 1, 2, 3, 4)
        ));
        arguments.add(Arguments.of(
                intDeqOf(1, 2, 3), intDeqOf(5, 6, 7, 8), 1, 8,
                List.of(3, 5), intDeqOf(5, 6, 7, 8, 1, 2, 3)
        ));
        // last - first
        arguments.add(Arguments.of(
                intDeqOf(1, 2, 3, 4), intDeqOf(5, 6, 7), 4, 5,
                List.of(1, 7), intDeqOf(1, 2, 3, 4, 5, 6, 7)
        ));
        arguments.add(Arguments.of(
                intDeqOf(1, 2, 3), intDeqOf(5, 6, 7, 8), 3, 5,
                List.of(1, 8), intDeqOf(1, 2, 3, 5, 6, 7, 8)
        ));
        // last - last
        arguments.add(Arguments.of(
                intDeqOf(1, 2, 3, 4), intDeqOf(5, 6, 7), 4, 7,
                List.of(1, 5), intDeqOf(1, 2, 3, 4, 7, 6, 5)
        ));
        arguments.add(Arguments.of(
                intDeqOf(1, 2, 3), intDeqOf(5, 6, 7, 8), 3, 8,
                List.of(5, 1), intDeqOf(5, 6, 7, 8, 3, 2, 1)
        ));
        return arguments.stream();
    }

    private static Stream<Arguments> argumentsForTestRestoreArray() {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(
                new int[][]{{2, 1}, {3, 4}, {3, 2}},
                new int[]{1, 2, 3, 4}
        ));
        arguments.add(Arguments.of(
                new int[][]{{4, -2}, {1, 4}, {-3, 1}},
                new int[]{-3, 1, 4, -2}
        ));
        return arguments.stream();
    }

    private static ArrayDeque<Integer> intDeqOf(int... values) {
        var deq = new ArrayDeque<Integer>();
        for (int value : values) {
            deq.offerLast(value);
        }
        return deq;
    }
}
