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

class LeetCode661Test {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestImageSmoother")
    @ParameterizedTest
    void testImageSmoother(int[][] expected, int[][] img) throws InterruptedException {
        var result = new LeetCode661().imageSmoother(img);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestImageSmoother() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(
                objectMapper.readValue("[[0,0,0],[0,0,0],[0,0,0]]", arrayOfArrayOfInt),
                objectMapper.readValue("[[1,1,1],[1,0,1],[1,1,1]]", arrayOfArrayOfInt)
        ));
        arguments.add(Arguments.of(
                objectMapper.readValue("[[137,141,137],[141,138,141],[137,141,137]]", arrayOfArrayOfInt),
                objectMapper.readValue("[[100,200,100],[200,50,200],[100,200,100]]", arrayOfArrayOfInt)
        ));
        return arguments.stream();
    }

    private static final TypeReference<int[][]> arrayOfArrayOfInt = new TypeReference<>() {
    };
}
