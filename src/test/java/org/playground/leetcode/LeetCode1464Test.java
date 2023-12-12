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

class LeetCode1464Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1464Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestMaxProduct")
    @ParameterizedTest
    void testMaxProduct(int expected, int[] nums) {
        var result = new LeetCode1464().maxProduct(nums);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestMaxProduct() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(12, new int[]{3, 4, 5, 2}));
        arguments.add(Arguments.of(16, new int[]{1, 5, 4, 5}));
        arguments.add(Arguments.of(12, new int[]{3, 7}));
        return arguments.stream();
    }
}
