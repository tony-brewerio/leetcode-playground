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

class LeetCode2931Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode2931Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestMinPairSum")
    @ParameterizedTest
    void testMinPairSum(int expected, String[] garbage, int[] travel) {
        var result = new LeetCode2931().garbageCollection(garbage, travel);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestMinPairSum() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(21, new String[]{"G", "P", "GP", "GG"}, new int[]{2, 4, 3}));
        arguments.add(Arguments.of(37, new String[]{"MMM", "PGM", "GP"}, new int[]{3, 10}));
        return arguments.stream();
    }
}
