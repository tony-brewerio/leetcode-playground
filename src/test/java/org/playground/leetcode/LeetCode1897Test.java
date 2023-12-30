package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1897Test {

    @MethodSource("argumentsForTestMakeEqual")
    @ParameterizedTest
    void testMakeEqual(boolean expected, String[] words) throws InterruptedException {
        var result = new LeetCode1897().makeEqual(words);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestMakeEqual() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(true, new String[]{"abc", "aabc", "bc"}));
        arguments.add(Arguments.of(false, new String[]{"ab", "a"}));
        return arguments.stream();
    }

}
