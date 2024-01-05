package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode300Test {

    @MethodSource("argumentsForTestLengthOfLIS")
    @ParameterizedTest
    void testLengthOfLIS(int expected, int[] nums) throws InterruptedException {
        var result = new LeetCode300().lengthOfLIS(nums);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestLengthOfLIS() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(4, new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        arguments.add(Arguments.of(4, new int[]{0, 1, 0, 3, 2, 3}));
        arguments.add(Arguments.of(1, new int[]{7, 7, 7, 7, 7, 7, 7}));
        return arguments.stream();
    }

}
