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

class LeetCode1266Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1266Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestMinTimeToVisitAllPoints")
    @ParameterizedTest
    void testMinTimeToVisitAllPoints(int expected, int[][] points) {
        var result = new LeetCode1266().minTimeToVisitAllPoints(points);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestMinTimeToVisitAllPoints() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(7, objectMapper.readValue("[[1,1],[3,4],[-1,0]]", arrayOfArrayOfIntTypRef)));
        arguments.add(Arguments.of(5, objectMapper.readValue("[[3,2],[-2,2]]", arrayOfArrayOfIntTypRef)));
        return arguments.stream();
    }

    private static final TypeReference<int[][]> arrayOfArrayOfIntTypRef = new TypeReference<int[][]>() {
    };
}
