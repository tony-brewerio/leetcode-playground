package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1155Test {

    @MethodSource("argumentsForTestTotalMoney")
    @ParameterizedTest
    void testTotalMoney(int expected, int n, int k, int target) throws InterruptedException {
        var result = new LeetCode1155().numRollsToTarget(n, k, target);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestTotalMoney() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(1, 1, 6, 3));
        arguments.add(Arguments.of(6, 2, 6, 7));
        arguments.add(Arguments.of(222616187, 30, 30, 500));
        return arguments.stream();
    }

}
