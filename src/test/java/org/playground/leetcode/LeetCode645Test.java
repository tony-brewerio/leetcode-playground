package org.playground.leetcode;

import com.fasterxml.jackson.core.type.TypeReference;
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

class LeetCode645Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode645Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestFindErrorNums")
    @ParameterizedTest
    void testFindErrorNums(int[] expected, int[] nums) {
        var result = new LeetCode645().findErrorNums(nums);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestFindErrorNums() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(new int[]{2, 3}, new int[]{1, 2, 2, 4}));
        arguments.add(Arguments.of(new int[]{1, 2}, new int[]{1, 1}));
        return arguments.stream();
    }

    private static final TypeReference<int[][]> arrayOfArrayOfInt = new TypeReference<>() {
    };
}
