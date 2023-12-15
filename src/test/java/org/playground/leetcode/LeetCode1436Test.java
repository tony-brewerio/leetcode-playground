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

class LeetCode1436Test {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestDestCity")
    @ParameterizedTest
    void testDestCity(String expected, List<List<String>> paths) throws InterruptedException {
        var result = new LeetCode1436().destCity(paths);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestDestCity() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of("Sao Paulo", objectMapper.readValue("""
                [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
                """, listOfListOfString)));
        arguments.add(Arguments.of("A", objectMapper.readValue("""
                [["B","C"],["D","B"],["C","A"]]
                 """, listOfListOfString)));
        arguments.add(Arguments.of("Z", objectMapper.readValue("""
                [["A","Z"]]
                """, listOfListOfString)));
        return arguments.stream();
    }

    private static final TypeReference<List<List<String>>> listOfListOfString = new TypeReference<>() {
    };

}
