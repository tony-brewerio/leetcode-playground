package org.playground.leetcode;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1424Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1424Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @CsvFileSource(resources = "/LeetCode1424Test.argumentsForTestFindDiagonalOrder.csv", delimiterString = ";", maxCharsPerColumn = 1_000_000)
    @ParameterizedTest(name = "case #{index}")
    void testFindDiagonalOrder(int n,
                               @ConvertWith(CSVArrayOfIntsConverter.class) int[] expected,
                               @ConvertWith(CSVListOfListsOfIntsConverter.class) List<List<Integer>> nums) {
        var result = new LeetCode1424().findDiagonalOrder(nums);
        assertThat(result).isEqualTo(expected);
//        if (n == 3) {
//                var result = new LeetCode1424().findDiagonalOrder(nums);
//                assertThat(result).isEqualTo(expected);
//            }
//        }
    }

    static class CSVArrayOfIntsConverter implements ArgumentConverter {
        private static final TypeReference<int[]> arrayOfInts = new TypeReference<>() {
        };

        @Override
        public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
            if (!(source instanceof String)) {
                throw new IllegalArgumentException("The argument should be a string: " + source);
            }
            try {
                return objectMapper.readValue((String) source, arrayOfInts);
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to convert", e);
            }
        }
    }

    static class CSVListOfListsOfIntsConverter implements ArgumentConverter {
        private static final TypeReference<List<List<Integer>>> listOfListsOfInts = new TypeReference<>() {
        };

        @Override
        public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
            if (!(source instanceof String)) {
                throw new IllegalArgumentException("The argument should be a string: " + source);
            }
            try {
                return objectMapper.readValue((String) source, listOfListsOfInts);
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to convert", e);
            }
        }
    }
}
