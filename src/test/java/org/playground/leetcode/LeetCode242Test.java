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

class LeetCode242Test {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestIsAnagram")
    @ParameterizedTest
    void testIsAnagram(boolean expected, String s, String t) throws InterruptedException {
        var result = new LeetCode242().isAnagram(s, t);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestIsAnagram() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(true, "anagram", "nagaram"));
        arguments.add(Arguments.of(false, "rat", "car"));
        return arguments.stream();
    }
}
