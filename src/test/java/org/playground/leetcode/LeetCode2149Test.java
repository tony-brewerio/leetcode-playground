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

class LeetCode2149Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode2149Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestRearrangeArray")
    @ParameterizedTest
    void testRearrangeArray(int[] expected, int[] nums) {
        var result = new LeetCode2149().rearrangeArray(nums);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestRearrangeArray() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(new int[]{3, -2, 1, -5, 2, -4}, new int[]{3, 1, -2, -5, 2, -4}));
        arguments.add(Arguments.of(new int[]{1, -1}, new int[]{-1, 1}));
        return arguments.stream();
    }
}
