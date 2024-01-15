package org.playground.leetcode;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode2225Test {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestFindWinners")
    @ParameterizedTest
    void testFindWinners(List<List<Integer>> expected, int[][] matches) throws InterruptedException {
        var result = new LeetCode2225().findWinners(matches);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestFindWinners() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(
                objectMapper.readValue("[[1,2,10],[4,5,7,8]]", listOfListOfInteger),
                objectMapper.readValue("[[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]", arrayOfArrayOfInt)
        ));
        arguments.add(Arguments.of(
                objectMapper.readValue("[[1,2,5,6],[]]", listOfListOfInteger),
                objectMapper.readValue("[[2,3],[1,3],[5,4],[6,4]]", arrayOfArrayOfInt)
        ));
        return arguments.stream();
    }

    private static final TypeReference<int[][]> arrayOfArrayOfInt = new TypeReference<>() {
    };
    private static final TypeReference<List<List<Integer>>> listOfListOfInteger = new TypeReference<>() {
    };
}
