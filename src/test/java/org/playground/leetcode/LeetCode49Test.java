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

class LeetCode49Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode49Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestFirstUniqChar")
    @ParameterizedTest
    void testFirstUniqChar(List<List<String>> expected, String[] strs) {
        var result = new LeetCode49().groupAnagrams(strs);
        Set<Set<String>> resultAsSet = result.stream().map(HashSet::new).collect(Collectors.toSet());
        Set<Set<String>> expectedAsSet = expected.stream().map(HashSet::new).collect(Collectors.toSet());
        assertThat(resultAsSet).isEqualTo(expectedAsSet);
    }

    private static Stream<Arguments> argumentsForTestFirstUniqChar() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(
                objectMapper.readValue("""
                        [["bat"],["nat","tan"],["ate","eat","tea"]]
                        """, listOfListString),
                objectMapper.readValue("""
                        ["eat","tea","tan","ate","nat","bat"]
                        """, arrayOfString)
        ));
        arguments.add(Arguments.of(
                objectMapper.readValue("""
                        [[""]]
                        """, listOfListString),
                objectMapper.readValue("""
                        [""]
                        """, arrayOfString)
        ));
        arguments.add(Arguments.of(
                objectMapper.readValue("""
                        [["a"]]
                        """, listOfListString),
                objectMapper.readValue("""
                        ["a"]
                        """, arrayOfString)
        ));
        return arguments.stream();
    }

    private static final TypeReference<String[]> arrayOfString = new TypeReference<>() {
    };

    private static final TypeReference<List<List<String>>> listOfListString = new TypeReference<>() {
    };

}
