package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1422Test {

    @MethodSource("argumentsForTestMaxScore")
    @ParameterizedTest
    void testMaxScore(int expected, String s) throws InterruptedException {
        var result = new LeetCode1422().maxScore(s);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestMaxScore() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(5, "011101"));
        arguments.add(Arguments.of(5, "00111"));
        arguments.add(Arguments.of(3, "1111"));
        return arguments.stream();
    }

}
