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

class LeetCode1887Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1887Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestMinPairSum")
    @ParameterizedTest
    void testMinPairSum(int expected, int[] nums) {
        var result = new LeetCode1887().reductionOperations(nums);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestMinPairSum() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(3, new int[]{5, 1, 3}));
        arguments.add(Arguments.of(0, new int[]{1, 1, 1}));
        arguments.add(Arguments.of(4, new int[]{1, 1, 2, 2, 3}));
        return arguments.stream();
    }
}
