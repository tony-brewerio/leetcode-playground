package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1688Test {

    @MethodSource("argumentsForTestNumberOfMatches")
    @ParameterizedTest
    void testNumberOfMatches(int expected, int n) throws InterruptedException {
        var result = new LeetCode1688().numberOfMatches(n);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestNumberOfMatches() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(6, 7));
        arguments.add(Arguments.of(13, 14));
        return arguments.stream();
    }

}
