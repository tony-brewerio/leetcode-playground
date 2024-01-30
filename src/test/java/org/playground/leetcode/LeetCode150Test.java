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

class LeetCode150Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode150Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestEvalRPN")
    @ParameterizedTest
    void testEvalRPN(int expected, String[] tokens) {
        var result = new LeetCode150().evalRPN(tokens);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestEvalRPN() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(9, new String[]{"2", "1", "+", "3", "*"}));
        arguments.add(Arguments.of(6, new String[]{"4", "13", "5", "/", "+"}));
        arguments.add(Arguments.of(22, new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
        return arguments.stream();
    }
}
