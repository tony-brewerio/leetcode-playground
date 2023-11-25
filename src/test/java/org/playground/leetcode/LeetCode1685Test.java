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

class LeetCode1685Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1685Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestGetSumAbsoluteDifferences")
    @ParameterizedTest
    void testGetSumAbsoluteDifferences(int[] expected, int[] nums) {
        var result = new LeetCode1685().getSumAbsoluteDifferences(nums);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestGetSumAbsoluteDifferences() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(new int[]{4, 3, 5}, new int[]{2, 3, 5}));
        arguments.add(Arguments.of(new int[]{24, 15, 13, 15, 21}, new int[]{1, 4, 6, 8, 10}));
        return arguments.stream();
    }
}
