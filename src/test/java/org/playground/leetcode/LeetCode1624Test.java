package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1624Test {

    @MethodSource("argumentsForTestMakeEqual")
    @ParameterizedTest
    void testMakeEqual(int expected, String s) throws InterruptedException {
        var result = new LeetCode1624().maxLengthBetweenEqualCharacters(s);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestMakeEqual() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(0, "aa"));
        arguments.add(Arguments.of(2, "abca"));
        arguments.add(Arguments.of(-1, "cbzxy"));
        arguments.add(Arguments.of(18, "mgntdygtxrvxjnwksqhxuxtrv"));
        return arguments.stream();
    }

}
