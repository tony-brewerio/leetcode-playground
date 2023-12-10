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

class LeetCode867Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode867Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestTranspose")
    @ParameterizedTest
    void testTranspose(int[][] expected, int[][] matrix) {
        var result = new LeetCode867().transpose(matrix);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestTranspose() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(
                objectMapper.readValue("[[1,4,7],[2,5,8],[3,6,9]]", arrayOfArrayOfInt),
                objectMapper.readValue("[[1,2,3],[4,5,6],[7,8,9]]", arrayOfArrayOfInt)
        ));
        arguments.add(Arguments.of(
                objectMapper.readValue("[[1,4],[2,5],[3,6]]", arrayOfArrayOfInt),
                objectMapper.readValue("[[1,2,3],[4,5,6]]", arrayOfArrayOfInt)
        ));
        return arguments.stream();
    }

    private static final TypeReference<int[][]> arrayOfArrayOfInt = new TypeReference<>() {
    };
}
