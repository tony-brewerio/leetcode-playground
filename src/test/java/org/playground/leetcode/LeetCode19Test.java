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

class LeetCode19Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode19Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestRemoveNthFromEnd")
    @ParameterizedTest
    void testRemoveNthFromEnd(int[] expected, int[] list, int n) {
        var result = new LeetCode19().removeNthFromEnd(LeetCode19.ListNode.of(list), n);
        assertThat(result != null ? result.toArray() : new int[0]).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestRemoveNthFromEnd() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(new int[]{1, 2, 3, 5}, new int[]{1, 2, 3, 4, 5}, 2));
        arguments.add(Arguments.of(new int[]{}, new int[]{1}, 1));
        arguments.add(Arguments.of(new int[]{1}, new int[]{1, 2}, 1));
        return arguments.stream();
    }
}
