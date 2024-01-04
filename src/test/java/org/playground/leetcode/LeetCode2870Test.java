package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode2870Test {

    @MethodSource("argumentsForTestMinOperations")
    @ParameterizedTest
    void testMinOperations(int expected, int[] nums) throws InterruptedException {
        var result = new LeetCode2870().minOperations(nums);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestMinOperations() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(4, new int[]{2, 3, 3, 2, 2, 4, 2, 3, 4}));
        arguments.add(Arguments.of(-1, new int[]{2, 1, 2, 2, 3, 3}));
        return arguments.stream();
    }

}
