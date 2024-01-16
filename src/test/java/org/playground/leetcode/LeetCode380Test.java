package org.playground.leetcode;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.playground.leetcode.converters.CSVArrayOfArrayOfIntConverter;
import org.playground.leetcode.converters.CSVArrayOfIntConverter;
import org.playground.leetcode.converters.CSVArrayOfStringConverter;
import org.playground.leetcode.converters.CSVListOfObjectConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode380Test {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @CsvFileSource(resources = "/LeetCode380Test.argumentsForTestRandomizedSet.csv", delimiterString = ";", maxCharsPerColumn = 10_000_000)
    @ParameterizedTest(name = "case #{index}")
    void testRandomizedSet(
            int ix,
            @ConvertWith(CSVListOfObjectConverter.class) List<Object> expected,
            @ConvertWith(CSVArrayOfStringConverter.class) String[] operations,
            @ConvertWith(CSVArrayOfArrayOfIntConverter.class) int[][] values
    ) throws InterruptedException {
        var set = new LeetCode380.RandomizedSet();
        var result = new ArrayList<>();
        for (int i = 0; i < operations.length; i++) {
            switch (operations[i]) {
                case "RandomizedSet":
//                    result.add(expected.get(i));
                    break;
                case "insert":
                    result.add(set.insert(values[i][0]));
                    break;
                case "remove":
                    result.add(set.remove(values[i][0]));
                    break;
                case "getRandom":
                    var random = set.getRandom();
                    assertThat(set.contains(random)).isTrue();
//                    result.add(expected.get(i));
                    break;
            }
        }
//        assertThat(result).isEqualTo(expected);
    }
}
