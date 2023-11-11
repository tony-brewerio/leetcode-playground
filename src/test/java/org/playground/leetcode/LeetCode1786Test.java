package org.playground.leetcode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1786Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1786Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @CsvFileSource(resources = "/LeetCode1786Test.argumentsForTestCountRestrictedPaths.csv", delimiterString = ";", maxCharsPerColumn = 1_000_000)
    @ParameterizedTest(name = "{index} => expected:{0} - n:{1} - edges:{2}")
    void testCountRestrictedPaths(long expected, int n, @ConvertWith(CommaDelimitedStringToIntArrayConverter.class) int[][] edges) {
        for (int i = 0; i < 100; i++) {
            var count = new LeetCode1786().countRestrictedPaths(n, edges);
            assertThat(count).isEqualTo(expected);
        }
    }

    static class CommaDelimitedStringToIntArrayConverter implements ArgumentConverter {
        @Override
        public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
            if (!(source instanceof String)) {
                throw new IllegalArgumentException("The argument should be a string: " + source);
            }
            try {
                return StreamSupport.stream(objectMapper.readTree((String) source).spliterator(), false)
                        .map(node -> (ArrayNode) node)
                        .map(node -> new int[]{node.get(0).intValue(), node.get(1).intValue(), node.get(2).intValue()})
                        .toArray(int[][]::new);
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to convert", e);
            }
        }
    }
}
