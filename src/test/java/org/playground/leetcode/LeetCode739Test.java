package org.playground.leetcode;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode739Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode739Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestEvalRPN")
    @ParameterizedTest
    void testEvalRPN(int[] expected, int[] temperatures) {
        var result = new LeetCode739().dailyTemperatures(temperatures);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestEvalRPN() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(new int[]{1, 1, 4, 2, 1, 1, 0, 0}, new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
        arguments.add(Arguments.of(new int[]{1, 1, 1, 0}, new int[]{30, 40, 50, 60}));
        arguments.add(Arguments.of(new int[]{1, 1, 0}, new int[]{30, 60, 90}));
        arguments.add(Arguments.of(new int[]{8, 1, 5, 4, 3, 2, 1, 1, 0, 0}, new int[]{89, 62, 70, 58, 47, 47, 46, 76, 100, 70}));
        return arguments.stream();
    }
}
