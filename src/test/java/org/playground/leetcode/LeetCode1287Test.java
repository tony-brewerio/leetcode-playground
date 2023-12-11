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

class LeetCode1287Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1287Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestFindSpecialInteger")
    @ParameterizedTest
    void testFindSpecialInteger(int expected, int[] arr) {
        var result = new LeetCode1287().findSpecialInteger(arr);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestFindSpecialInteger() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(6, new int[]{1, 2, 2, 6, 6, 6, 6, 7, 10}));
        arguments.add(Arguments.of(1, new int[]{1, 1}));
        arguments.add(Arguments.of(3, new int[]{1, 1, 2, 2, 3, 3, 3, 3}));
        return arguments.stream();
    }
}
