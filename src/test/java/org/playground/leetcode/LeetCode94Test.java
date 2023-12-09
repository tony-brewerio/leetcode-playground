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

class LeetCode94Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode94Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestInorderTraversal")
    @ParameterizedTest
    void testInorderTraversal(List<Integer> expected, LeetCode94.TreeNode root) {
        var result = new LeetCode94().inorderTraversal(root);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestInorderTraversal() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(
                List.of(1, 3, 2),
                new LeetCode94.TreeNode(
                        1,
                        null,
                        new LeetCode94.TreeNode(
                                2,
                                new LeetCode94.TreeNode(3),
                                null
                        )
                )
        ));
        arguments.add(Arguments.of(
                List.of(),
                null
        ));
        arguments.add(Arguments.of(
                List.of(1),
                new LeetCode94.TreeNode(1)
        ));
        return arguments.stream();
    }
}
