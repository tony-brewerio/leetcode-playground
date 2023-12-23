package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1496Test {

    @MethodSource("argumentsForTestIsPathCrossing")
    @ParameterizedTest
    void testIsPathCrossing(boolean expected, String path) throws InterruptedException {
        var result = new LeetCode1496().isPathCrossing(path);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestIsPathCrossing() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(false, "NES"));
        arguments.add(Arguments.of(true, "NESWW"));
        return arguments.stream();
    }

}
