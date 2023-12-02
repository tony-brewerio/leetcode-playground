package org.playground.leetcode;

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

class LeetCode1160Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1160Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestCountCharacters")
    @ParameterizedTest
    void testCountCharacters(int expected, String[] words, String chars) {
        var result = new LeetCode1160().countCharacters(words, chars);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestCountCharacters() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(6, new String[]{"cat", "bt", "hat", "tree"}, "atach"));
        arguments.add(Arguments.of(10, new String[]{"hello", "world", "leetcode"}, "welldonehoneyr"));
        return arguments.stream();
    }
}
