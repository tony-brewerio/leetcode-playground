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

class LeetCode1758Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1758Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestMinOperations")
    @ParameterizedTest
    void testMinOperations(int expected, String s) {
        var result = new LeetCode1758().minOperations(s);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestMinOperations() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(1, "0100"));
        arguments.add(Arguments.of(0, "10"));
        arguments.add(Arguments.of(2, "1111"));
        return arguments.stream();
    }
}
