package org.playground.leetcode;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.playground.leetcode.converters.CSVArrayOfIntConverter;
import org.playground.leetcode.converters.CSVListOfIntegersConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode368Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode368Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @CsvFileSource(resources = "/LeetCode368Test.argumentsForTestLargestDivisibleSubset.csv", delimiterString = ";", maxCharsPerColumn = 1_000_000)
    @ParameterizedTest(name = "{index}")
    void testLargestDivisibleSubset(int i, @ConvertWith(CSVListOfIntegersConverter.class) List<Integer> expected, @ConvertWith(CSVArrayOfIntConverter.class) int[] nums) {
        if (i != 3) {
            return;
        }
        var result = new LeetCode368().largestDivisibleSubset(nums);
        assertThat(result).isEqualTo(expected);
    }
}
