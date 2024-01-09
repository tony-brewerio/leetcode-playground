package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.playground.leetcode.LeetCode872.TreeNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode872Test {

    @MethodSource("argumentsForTestLeafSimilar")
    @ParameterizedTest
    void testLeafSimilar(boolean expected, LeetCode872.TreeNode root1, LeetCode872.TreeNode root2) throws InterruptedException {
        var result = new LeetCode872().leafSimilar(root1, root2);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestLeafSimilar() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(
                true,
                new TreeNode(
                        3,
                        new TreeNode(
                                5,
                                new TreeNode(6),
                                new TreeNode(
                                        2,
                                        new TreeNode(7),
                                        new TreeNode(4)
                                )
                        ),
                        new TreeNode(
                                1,
                                new TreeNode(9),
                                new TreeNode(8)
                        )
                ),
                new TreeNode(
                        3,
                        new TreeNode(
                                5,
                                new TreeNode(6),
                                new TreeNode(7)
                        ),
                        new TreeNode(
                                1,
                                new TreeNode(4),
                                new TreeNode(
                                        2,
                                        new TreeNode(9),
                                        new TreeNode(8)
                                )
                        )
                )
        ));
        arguments.add(Arguments.of(
                false,
                new TreeNode(1, new TreeNode(2), new TreeNode(3)),
                new TreeNode(1, new TreeNode(3), new TreeNode(2))
        ));
        return arguments.stream();
    }

}
