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

class LeetCode1838Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1838Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestMaxFrequency")
    @ParameterizedTest
    void testMaxFrequency(int expected, int[] nums, int k) {
        var result = new LeetCode1838().maxFrequency(nums, k);
        assertThat(result).isEqualTo(expected);
    }

    @MethodSource("argumentsForTestMaxFrequency")
    @ParameterizedTest
    void testMaxFrequencySimple(int expected, int[] nums, int k) {
        var result = new LeetCode1838().maxFrequencySimple(nums, k);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestMaxFrequency() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(3, new int[]{1, 2, 4}, 5));
        arguments.add(Arguments.of(3, new int[]{3, 5, 2, 3}, 5));
        arguments.add(Arguments.of(2, new int[]{1, 4, 8, 13}, 5));
        arguments.add(Arguments.of(22, new int[]{9940, 9995, 9944, 9937, 9941, 9952, 9907, 9952, 9987, 9964, 9940, 9914, 9941, 9933, 9912, 9934, 9980, 9907, 9980, 9944, 9910, 9997}, 7925));
        return arguments.stream();
    }
}
