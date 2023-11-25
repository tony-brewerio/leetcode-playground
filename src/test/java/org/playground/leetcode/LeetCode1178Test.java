package org.playground.leetcode;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.playground.leetcode.converters.CSVArrayOfStringsConverter;
import org.playground.leetcode.converters.CSVListOfIntegersConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1178Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1178Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @CsvFileSource(resources = "/LeetCode1178Test.argumentsForTestFindNumOfValidWords.csv", delimiterString = ";", maxCharsPerColumn = 10_000_000)
    @ParameterizedTest(name = "case #{index}")
    void testFindNumOfValidWords(int i,
                                 @ConvertWith(CSVListOfIntegersConverter.class) List<Integer> expected,
                                 @ConvertWith(CSVArrayOfStringsConverter.class) String[] words,
                                 @ConvertWith(CSVArrayOfStringsConverter.class) String[] puzzles) {
        var result = new LeetCode1178().findNumOfValidWords(words, puzzles);
        assertThat(result).isEqualTo(expected);
    }

    @CsvFileSource(resources = "/LeetCode1178Test.argumentsForTestFindNumOfValidWords.csv", delimiterString = ";", maxCharsPerColumn = 10_000_000)
    @ParameterizedTest(name = "case #{index}")
    void testFindNumOfValidWordsUsingTrie(int i,
                                          @ConvertWith(CSVListOfIntegersConverter.class) List<Integer> expected,
                                          @ConvertWith(CSVArrayOfStringsConverter.class) String[] words,
                                          @ConvertWith(CSVArrayOfStringsConverter.class) String[] puzzles) {

        var result = new LeetCode1178UsingTrie().findNumOfValidWords(words, puzzles);
        assertThat(result).isEqualTo(expected);

    }

}
