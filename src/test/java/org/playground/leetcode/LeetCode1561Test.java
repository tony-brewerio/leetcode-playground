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

class LeetCode1561Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1561Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestMaxCoins")
    @ParameterizedTest
    void testMaxCoins(int expected, int[] piles) {
        var result = new LeetCode1561().maxCoins(piles);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestMaxCoins() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(9, new int[]{2, 4, 1, 2, 7, 8}));
        arguments.add(Arguments.of(4, new int[]{2, 4, 5}));
        arguments.add(Arguments.of(18, new int[]{9, 8, 7, 6, 5, 1, 2, 3, 4}));
        return arguments.stream();
    }
}
