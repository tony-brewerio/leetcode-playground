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

class LeetCode1582Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1582Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestNumSpecial")
    @ParameterizedTest
    void testNumSpecial(int expected, int[][] mat) {
        var result = new LeetCode1582().numSpecial(mat);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestNumSpecial() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(1, objectMapper.readValue("[[1,0,0],[0,0,1],[1,0,0]]", arrayOfArrayOfInt)));
        arguments.add(Arguments.of(3, objectMapper.readValue("[[1,0,0],[0,1,0],[0,0,1]]", arrayOfArrayOfInt)));
        arguments.add(Arguments.of(
                1,
                objectMapper.readValue("[[0,0,0,0,0,1,0,0],[0,0,0,0,1,0,0,1],[0,0,0,0,1,0,0,0],[1,0,0,0,1,0,0,0],[0,0,1,1,0,0,0,0]]", arrayOfArrayOfInt)
        ));
        return arguments.stream();
    }

    private static final TypeReference<int[][]> arrayOfArrayOfInt = new TypeReference<>() {
    };
}
