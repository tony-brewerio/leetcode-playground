package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1662Test {

    @MethodSource("argumentsForTestArrayStringsAreEqual")
    @ParameterizedTest
    void testArrayStringsAreEqual(boolean expected, String[] word1, String[] word2) throws InterruptedException {
        var result = new LeetCode1662().arrayStringsAreEqual(word1, word2);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestArrayStringsAreEqual() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(true, new String[]{"ab", "c"}, new String[]{"a", "bc"}));
        arguments.add(Arguments.of(false, new String[]{"a", "cb"}, new String[]{"ab", "c"}));
        arguments.add(Arguments.of(true, new String[]{"abc", "d", "defg"}, new String[]{"abcddefg"}));
        return arguments.stream();
    }

}
