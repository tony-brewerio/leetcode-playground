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

class LeetCode931Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode931Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestMinFallingPathSum")
    @ParameterizedTest
    void testMinFallingPathSum(int expected, int[][] matrix) {
        var result = new LeetCode931().minFallingPathSum(matrix);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestMinFallingPathSum() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(13, objectMapper.readValue("[[2,1,3],[6,5,4],[7,8,9]]", arrayOfArrayOfInt)));
        arguments.add(Arguments.of(-59, objectMapper.readValue("[[-19,57],[-40,-5]]", arrayOfArrayOfInt)));
        return arguments.stream();
    }

    private static final TypeReference<int[][]> arrayOfArrayOfInt = new TypeReference<>() {
    };
}
