package org.playground.leetcode;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.playground.leetcode.converters.CSVArrayOfIntConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode45Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode45Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @CsvFileSource(resources = "/LeetCode45Test.argumentsForTestJump.csv", delimiterString = ";", maxCharsPerColumn = 1_000_000)
    @ParameterizedTest(name = "case #{index}")
    void testJump(int i, int expected, @ConvertWith(CSVArrayOfIntConverter.class) int[] nums) {
        var result = new LeetCode45().jump(nums);
        assertThat(result).isEqualTo(expected);
    }
}
