package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode2125Test {

    @MethodSource("argumentsForTestNumberOfBeams")
    @ParameterizedTest
    void testNumberOfBeams(int expected, String[] bank) throws InterruptedException {
        var result = new LeetCode2125().numberOfBeams(bank);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestNumberOfBeams() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(8, new String[]{"011001", "000000", "010100", "001000"}));
        arguments.add(Arguments.of(0, new String[]{"000", "111", "000"}));
        return arguments.stream();
    }

}
