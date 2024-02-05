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

class LeetCode387Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode387Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestFirstUniqChar")
    @ParameterizedTest
    void testFirstUniqChar(int expected, String s) {
        var result = new LeetCode387().firstUniqChar(s);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestFirstUniqChar() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(0, "leetcode"));
        arguments.add(Arguments.of(2, "loveleetcode"));
        arguments.add(Arguments.of(-1, "aabb"));
        return arguments.stream();
    }
}
