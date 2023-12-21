package org.playground.leetcode;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1637Test {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestMaxWidthOfVerticalArea")
    @ParameterizedTest
    void testMaxWidthOfVerticalArea(int expected, int[][] points) throws InterruptedException {
        var result = new LeetCode1637().maxWidthOfVerticalArea(points);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestMaxWidthOfVerticalArea() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(
                1,
                objectMapper.readValue("[[8,7],[9,9],[7,4],[9,7]]", arrayOfArrayOfInt)
        ));
        arguments.add(Arguments.of(
                3,
                objectMapper.readValue("[[3,1],[9,0],[1,0],[1,4],[5,3],[8,8]]", arrayOfArrayOfInt)
        ));
        return arguments.stream();
    }

    private static final TypeReference<int[][]> arrayOfArrayOfInt = new TypeReference<>() {
    };
}
