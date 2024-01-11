package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.playground.leetcode.LeetCode1026.TreeNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode1026Test {

    @MethodSource("argumentsForTestMaxAncestorDiff")
    @ParameterizedTest
    void testMaxAncestorDiff(int expected, TreeNode root) throws InterruptedException {
        var result = new LeetCode1026().maxAncestorDiff(root);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestMaxAncestorDiff() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(
                7,
                new TreeNode(
                        8,
                        new TreeNode(
                                3,
                                new TreeNode(1),
                                new TreeNode(
                                        6,
                                        new TreeNode(4),
                                        new TreeNode(7)
                                )
                        ),
                        new TreeNode(
                                10,
                                null,
                                new TreeNode(
                                        14,
                                        new TreeNode(13),
                                        null
                                )
                        )
                )
        ));
        arguments.add(Arguments.of(
                3,
                new TreeNode(
                        1,
                        null,
                        new TreeNode(
                                2,
                                null,
                                new TreeNode(
                                        0,
                                        new TreeNode(3),
                                        null
                                )
                        )
                )
        ));
        return arguments.stream();
    }

}
