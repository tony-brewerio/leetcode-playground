package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1716Test {

    @MethodSource("argumentsForTestTotalMoney")
    @ParameterizedTest
    void testTotalMoney(int expected, int n) throws InterruptedException {
        var result = new LeetCode1716().totalMoney(n);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestTotalMoney() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(10, 4));
        arguments.add(Arguments.of(28, 7));
        arguments.add(Arguments.of(37, 10));
        arguments.add(Arguments.of(63, 14));
        arguments.add(Arguments.of(96, 20));
        return arguments.stream();
    }

}
