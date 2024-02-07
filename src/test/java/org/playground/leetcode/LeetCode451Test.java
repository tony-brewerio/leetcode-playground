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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode451Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode451Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestFrequencySort")
    @ParameterizedTest
    void testFrequencySort(String expected, String s) {
        var result = new LeetCode451().frequencySort(s);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestFrequencySort() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of("eert", "tree"));
        arguments.add(Arguments.of("aaaccc", "cccaaa"));
        arguments.add(Arguments.of("bbAa", "Aabb"));
        return arguments.stream();
    }
}
