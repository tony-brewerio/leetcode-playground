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

class LeetCode1877Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1877Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestMinPairSum")
    @ParameterizedTest
    void testMinPairSum(int expected, int[] nums) {
        var result = new LeetCode1877().minPairSum(nums);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestMinPairSum() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(7, new int[]{3, 5, 2, 3}));
        arguments.add(Arguments.of(8, new int[]{3, 5, 4, 2, 4, 6}));
        arguments.add(Arguments.of(8, new int[]{4, 1, 5, 1, 2, 5, 1, 5, 5, 4}));
        return arguments.stream();
    }
}
