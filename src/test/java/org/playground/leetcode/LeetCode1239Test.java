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
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1239Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1239Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestMaxLength")
    @ParameterizedTest
    void testMaxLength(int expected, List<String> arr) {
        var result = new LeetCode1239().maxLength(arr);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestMaxLength() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(4, List.of("un", "iq", "ue")));
        arguments.add(Arguments.of(6, List.of("cha", "r", "act", "ers")));
        arguments.add(Arguments.of(26, List.of("abcdefghijklmnopqrstuvwxyz")));
        arguments.add(Arguments.of(0, List.of("aa", "bb")));
        return arguments.stream();
    }

    private static final TypeReference<int[][]> arrayOfArrayOfInt = new TypeReference<>() {
    };
}
