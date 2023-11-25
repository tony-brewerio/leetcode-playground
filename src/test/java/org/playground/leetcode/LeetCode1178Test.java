package org.playground.leetcode;

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

class LeetCode1178Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1178Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestFindNumOfValidWords")
    @ParameterizedTest
    void testFindNumOfValidWords(List<Integer> expected, String[] words, String[] puzzles) {
        var result = new LeetCode1178().findNumOfValidWords(words, puzzles);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestFindNumOfValidWords() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(
                List.of(1, 1, 3, 2, 4, 0),
                new String[]{"aaaa", "asas", "able", "ability", "actt", "actor", "access"},
                new String[]{"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"}
        ));
        arguments.add(Arguments.of(
                List.of(0, 1, 3, 2, 0),
                new String[]{"apple", "pleas", "please"},
                new String[]{"aelwxyz", "aelpxyz", "aelpsxy", "saelpxy", "xaelpsy"}
        ));
        return arguments.stream();
    }
}
