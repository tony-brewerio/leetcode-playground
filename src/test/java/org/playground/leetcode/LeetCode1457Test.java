package org.playground.leetcode;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.playground.leetcode.LeetCode1457.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1457Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode1457Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestPseudoPalindromicPaths")
    @ParameterizedTest
    void testPseudoPalindromicPaths(int expected, TreeNode root) {
        var result = new LeetCode1457().pseudoPalindromicPaths(root);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestPseudoPalindromicPaths() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(
                2,
                new TreeNode(
                        2,
                        new TreeNode(
                                3,
                                new TreeNode(3),
                                new TreeNode(1)
                        ),
                        new TreeNode(
                                1,
                                null,
                                new TreeNode(1)
                        )
                )
        ));
        arguments.add(Arguments.of(
                1,
                new TreeNode(
                        2,
                        new TreeNode(
                                1,
                                new TreeNode(1),
                                new TreeNode(
                                        3,
                                        null,
                                        new TreeNode(1)
                                )
                        ),
                        new TreeNode(1)
                )
        ));
        return arguments.stream();
    }
}
