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

class LeetCode76Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode76Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestKnightDialer")
    @ParameterizedTest
    void testKnightDialer(String expected, String s, String t) {
        var result = new LeetCode76().minWindow(s, t);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestKnightDialer() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of("BANC", "ADOBECODEBANC", "ABC"));
        arguments.add(Arguments.of("a", "a", "a"));
        arguments.add(Arguments.of("", "a", "aa"));
        return arguments.stream();
    }
}
