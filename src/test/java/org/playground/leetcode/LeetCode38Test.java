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

class LeetCode38Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode38Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestCountAndSay")
    @ParameterizedTest
    void testCountAndSay(String expected, int n) {
        var result = new LeetCode38().countAndSay(n);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestCountAndSay() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of("1", 1));
        arguments.add(Arguments.of("1211", 4));
        return arguments.stream();
    }
}
