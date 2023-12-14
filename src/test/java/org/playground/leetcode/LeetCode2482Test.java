package org.playground.leetcode;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode2482Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode2482Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestGetSumAbsoluteDifferences")
    @ParameterizedTest
    void testGetSumAbsoluteDifferences(int[][] expected, int[][] grid) {
        var result = new LeetCode2482().onesMinusZeros(grid);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestGetSumAbsoluteDifferences() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(
                objectMapper.readValue("[[0,0,4],[0,0,4],[-2,-2,2]]", arrayOfArrayOfInt),
                objectMapper.readValue("[[0,1,1],[1,0,1],[0,0,1]]", arrayOfArrayOfInt)
        ));
        arguments.add(Arguments.of(
                objectMapper.readValue("[[5,5,5],[5,5,5]]", arrayOfArrayOfInt),
                objectMapper.readValue("[[1,1,1],[1,1,1]]", arrayOfArrayOfInt)
        ));
        return arguments.stream();
    }

    private static final TypeReference<int[][]> arrayOfArrayOfInt = new TypeReference<>() {
    };
}
