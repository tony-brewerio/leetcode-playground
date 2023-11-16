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

class LeetCode1980Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1980Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestFindDifferentBinaryString")
    @ParameterizedTest
    void testFindDifferentBinaryString(String ignored, String[] nums) {
        var result = new LeetCode1980().findDifferentBinaryString(nums);
        assertThat(nums).doesNotContain(result);
    }

    private static Stream<Arguments> argumentsForTestFindDifferentBinaryString() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(null, new String[]{"01", "10"}));
        arguments.add(Arguments.of(null, new String[]{"00", "01"}));
        arguments.add(Arguments.of(null, new String[]{"111", "011", "001"}));
        return arguments.stream();
    }
}
