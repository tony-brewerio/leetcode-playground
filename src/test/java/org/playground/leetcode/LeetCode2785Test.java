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

class LeetCode2785Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode2785Test.class);

    @AfterEach
    public void resetAveragingOperationTimer() {
        AveragingOperationTimer.reset();
    }

    @MethodSource("argumentsForTestSortVowels")
    @ParameterizedTest
    void testSortVowels(String expected, String str) {
        var result = new LeetCode2785().sortVowels(str);
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> argumentsForTestSortVowels() {
        return Stream.of(
                Arguments.of("lEOtcede", "lEetcOde"),
                Arguments.of("lYmpH", "lYmpH")
        );
    }
}
