package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1455Test {

    @MethodSource("argumentsForTestFindContentChildren")
    @ParameterizedTest
    void testFindContentChildren(int expected, int[] g, int[] s) throws InterruptedException {
        var result = new LeetCode1455().findContentChildren(g, s);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestFindContentChildren() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(1, new int[]{1, 2, 3}, new int[]{1, 1}));
        arguments.add(Arguments.of(2, new int[]{1, 2}, new int[]{1, 2, 3}));
        return arguments.stream();
    }

}
