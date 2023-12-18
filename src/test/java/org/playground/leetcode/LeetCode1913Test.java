package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1913Test {

    @MethodSource("argumentsForTestMaxProductDifference")
    @ParameterizedTest
    void testMaxProductDifference(int expected, int[] nums) throws InterruptedException {
        var result = new LeetCode1913().maxProductDifference(nums);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestMaxProductDifference() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(34, new int[]{5, 6, 2, 7, 4}));
        arguments.add(Arguments.of(64, new int[]{4, 2, 5, 9, 7, 4, 8}));
        return arguments.stream();
    }

}
