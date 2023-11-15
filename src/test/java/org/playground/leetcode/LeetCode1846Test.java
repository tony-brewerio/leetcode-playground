package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1846Test {

    @MethodSource("argumentsForTestMaximumElementAfterDecrementingAndRearranging")
    @ParameterizedTest
    void testMaximumElementAfterDecrementingAndRearranging(int n, int[] arr) throws InterruptedException {
        var result = new LeetCode1846().maximumElementAfterDecrementingAndRearranging(arr);
        assertThat(result).isEqualTo(n);
    }

    private static Stream<Arguments> argumentsForTestMaximumElementAfterDecrementingAndRearranging() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(2, new int[]{2, 2, 1, 2, 1}));
        arguments.add(Arguments.of(3, new int[]{100, 1, 1000}));
        arguments.add(Arguments.of(5, new int[]{1, 2, 3, 4, 5}));
        return arguments.stream();
    }

}
