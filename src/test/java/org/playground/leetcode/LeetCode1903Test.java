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

class LeetCode1903Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1903Test.class);

    @AfterEach
    public void resetAveragingOperationTimer() {
        AveragingOperationTimer.reset();
    }

    @MethodSource("argumentsForTestLargestOddNumber")
    @ParameterizedTest
    void testLargestOddNumber(String expected, String num) {
        var result = new LeetCode1903().largestOddNumber(num);
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> argumentsForTestLargestOddNumber() {
        return Stream.of(
                Arguments.of("5", "52"),
                Arguments.of("", "4206"),
                Arguments.of("35427", "35427")
        );
    }
}
