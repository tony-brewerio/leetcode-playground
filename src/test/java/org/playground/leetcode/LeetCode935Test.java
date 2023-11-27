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

class LeetCode935Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode935Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestKnightDialer")
    @ParameterizedTest
    void testKnightDialer(int i, int expected, int n) {
        var result = new LeetCode935().knightDialer(n);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestKnightDialer() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(1, 10, 1));
        arguments.add(Arguments.of(2, 20, 2));
        arguments.add(Arguments.of(3, 136006598, 3131));
        return arguments.stream();
    }
}
