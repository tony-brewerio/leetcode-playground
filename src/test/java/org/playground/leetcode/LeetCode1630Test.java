package org.playground.leetcode;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1630Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1630Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestCheckArithmeticSubarrays")
    @ParameterizedTest
    void testCheckArithmeticSubarrays(List<Boolean> expected, int[] nums, int[] l, int[] r) {
        var result = new LeetCode1630().checkArithmeticSubarrays(nums, l, r);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestCheckArithmeticSubarrays() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(
                List.of(true, false, true),
                new int[]{4, 6, 5, 9, 3, 7},
                new int[]{0, 0, 2}, new int[]{2, 3, 5}
        ));
        arguments.add(Arguments.of(
                List.of(false, true, false, false, true, true),
                new int[]{-12, -9, -3, -12, -6, 15, 20, -25, -20, -15, -10},
                new int[]{0, 1, 6, 4, 8, 7}, new int[]{4, 4, 9, 7, 9, 10}
        ));
        return arguments.stream();
    }
}
