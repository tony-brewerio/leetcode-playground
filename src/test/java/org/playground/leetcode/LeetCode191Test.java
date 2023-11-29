package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode191Test {

    @MethodSource("argumentsForTestHammingWeight")
    @ParameterizedTest
    void testHammingWeight(int expected, int n) {
        var result = new LeetCode191().hammingWeight(n);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestHammingWeight() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(3, 0b00000000000000000000000000001011));
        arguments.add(Arguments.of(1, 0b00000000000000000000000010000000));
        arguments.add(Arguments.of(31, 0b11111111111111111111111111111101));
        return arguments.stream();
    }

}
