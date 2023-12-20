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

class LeetCode2706Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode2706Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestBuyChoco")
    @ParameterizedTest
    void testBuyChoco(int expected, int[] prices, int money) {
        var result = new LeetCode2706().buyChoco(prices, money);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestBuyChoco() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(0, new int[]{1, 2, 2}, 3));
        arguments.add(Arguments.of(3, new int[]{3, 2, 3}, 3));
        return arguments.stream();
    }
}
