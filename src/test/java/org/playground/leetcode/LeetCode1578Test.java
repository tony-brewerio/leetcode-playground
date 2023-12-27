package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1578Test {

    @MethodSource("argumentsForTestMinCost")
    @ParameterizedTest
    void testMinCost(int expected, String colors, int[] neededTime) throws InterruptedException {
        var result = new LeetCode1578().minCost(colors, neededTime);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestMinCost() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(3, "abaac", new int[]{1, 2, 3, 4, 5}));
        arguments.add(Arguments.of(0, "abc", new int[]{1, 2, 3}));
        arguments.add(Arguments.of(2, "aabaa", new int[]{1, 2, 3, 4, 1}));
        arguments.add(Arguments.of(26, "aaabbbabbbb", new int[]{3, 5, 10, 7, 5, 3, 5, 5, 4, 8, 1}));
        return arguments.stream();
    }

}
