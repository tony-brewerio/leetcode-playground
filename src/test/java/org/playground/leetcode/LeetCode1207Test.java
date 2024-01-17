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

class LeetCode1207Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1207Test.class);

    @MethodSource("argumentsForTestUniqueOccurrences")
    @ParameterizedTest
    void testUniqueOccurrences(boolean expected, int[] arr) throws InterruptedException {
        var result = new LeetCode1207().uniqueOccurrences(arr);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestUniqueOccurrences() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(true, new int[]{1, 2, 2, 1, 1, 3}));
        arguments.add(Arguments.of(false, new int[]{1, 2}));
        arguments.add(Arguments.of(true, new int[]{-3, 0, 1, -3, 1, 1, 1, -3, 10, 0}));
        return arguments.stream();
    }

}
