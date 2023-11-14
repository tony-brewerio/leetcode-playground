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

class LeetCode1930Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1930Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestCountPalindromicSubsequence")
    @ParameterizedTest
    void testCountPalindromicSubsequence(int n, String s) throws InterruptedException {
        var result = new LeetCode1930().countPalindromicSubsequence(s);
        assertThat(result).isEqualTo(n);
    }

    @MethodSource("argumentsForTestCountPalindromicSubsequence")
    @ParameterizedTest
    void testCountPalindromicSubsequenceV2(int n, String s) throws InterruptedException {
        var result = new LeetCode1930V2().countPalindromicSubsequence(s);
        assertThat(result).isEqualTo(n);
    }

    private static Stream<Arguments> argumentsForTestCountPalindromicSubsequence() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(3, "aabca"));
        arguments.add(Arguments.of(0, "adc"));
        arguments.add(Arguments.of(4, "bbcbaba"));
        return arguments.stream();
    }
}
