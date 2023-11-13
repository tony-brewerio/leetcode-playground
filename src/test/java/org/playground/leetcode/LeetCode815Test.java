package org.playground.leetcode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.playground.leetcode.helpers.AveragingOperationTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode815Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode815Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @AfterEach
    public void resetAveragingOperationTimer() {
        AveragingOperationTimer.reset();
    }

    @CsvFileSource(resources = "/LeetCode815Test.argumentsForTestNumBusesToDestination.csv", delimiterString = ";", maxCharsPerColumn = 1_000_000)
    @ParameterizedTest(name = "{index} => expected:{0} - source:{1} - target:{2} - routes:{3}")
    void testNumBusesToDestination(long expected, int source, int target, @ConvertWith(JsonNestedIntArrayConverter.class) int[][] routes) {
        var result = new LeetCode815().numBusesToDestination(routes, source, target);
        assertThat(result).isEqualTo(expected);
    }

    static class JsonNestedIntArrayConverter implements ArgumentConverter {
        @Override
        public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
            try {
                return objectMapper.readValue((String) source, new TypeReference<int[][]>() {
                });
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException("Failed to convert", e);
            }
        }
    }
}
