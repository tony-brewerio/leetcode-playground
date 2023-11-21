package org.playground.leetcode;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.playground.leetcode.helpers.AveragingOperationTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1814Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1814Test.class);

    @AfterEach
    public void resetAveragingOperationTimer() {
        AveragingOperationTimer.reset();
    }

    @MethodSource("argumentsForTestCountNicePairs")
    @ParameterizedTest
    void testCountNicePairs(int expected, int[] nums) {
        var result = new LeetCode1814().countNicePairs(nums);
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> argumentsForTestCountNicePairs() {
        return Stream.of(
                Arguments.of(2, new int[]{42, 11, 1, 97}),
                Arguments.of(4, new int[]{13, 10, 35, 24, 76})
        );
    }
}
