package org.playground.leetcode;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1785Test {

    @CsvFileSource(resources = "/LeetCode1785Test.argumentsForTestMinElements.csv", delimiterString = ";", maxCharsPerColumn = 1_000_000)
    @ParameterizedTest(name = "{index} => limit:{1} : goal:{2} : expected:{0}")
    void testMinElements(int expected, int limit, int goal, @ConvertWith(CommaDelimitedStringToIntArrayConverter.class) int[] nums) {
        int min = new LeetCode1785().minElements(nums, limit, goal);
        assertThat(min).isEqualTo(expected);
    }

    static class CommaDelimitedStringToIntArrayConverter implements ArgumentConverter {
        @Override
        public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
            if (!(source instanceof String)) {
                throw new IllegalArgumentException("The argument should be a string: " + source);
            }
            try {
                String[] parts = ((String) source).split(",");
                return Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to convert", e);
            }
        }
    }
}
