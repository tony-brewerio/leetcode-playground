package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1704Test {

    @MethodSource("argumentsForTestHalvesAreAlike")
    @ParameterizedTest
    void testHalvesAreAlike(boolean expected, String s) throws InterruptedException {
        var result = new LeetCode1704().halvesAreAlike(s);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestHalvesAreAlike() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(true, "book"));
        arguments.add(Arguments.of(false, "textbook"));
        arguments.add(Arguments.of(true, "AbCdEfGh"));
        return arguments.stream();
    }

}
