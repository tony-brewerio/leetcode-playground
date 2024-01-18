package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode70Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode70Test.class);

    @MethodSource("argumentsForTestClimbStairs")
    @ParameterizedTest
    void testClimbStairs(int expected, int n) throws InterruptedException {
        var result = new LeetCode70().climbStairs(n);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestClimbStairs() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(2, 2));
        arguments.add(Arguments.of(3, 3));
        return arguments.stream();
    }

}
