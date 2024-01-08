package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode938Test {

    @MethodSource("argumentsForTestRangeSumBST")
    @ParameterizedTest
    void testRangeSumBST(int expected, LeetCode938.TreeNode root, int low, int high) throws InterruptedException {
        var result = new LeetCode938().rangeSumBST(root, low, high);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestRangeSumBST() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(
                32,
                new LeetCode938.TreeNode(
                        10,
                        new LeetCode938.TreeNode(
                                5,
                                new LeetCode938.TreeNode(3),
                                new LeetCode938.TreeNode(7)
                        ),
                        new LeetCode938.TreeNode(
                                15,
                                null,
                                new LeetCode938.TreeNode(18)
                        )
                ),
                7, 15
        ));
        arguments.add(Arguments.of(
                23,
                new LeetCode938.TreeNode(
                        10,
                        new LeetCode938.TreeNode(
                                5,
                                new LeetCode938.TreeNode(
                                        3,
                                        new LeetCode938.TreeNode(1),
                                        null
                                ),
                                new LeetCode938.TreeNode(
                                        7,
                                        new LeetCode938.TreeNode(6),
                                        null
                                )
                        ),
                        new LeetCode938.TreeNode(
                                15,
                                new LeetCode938.TreeNode(13),
                                new LeetCode938.TreeNode(18)
                        )
                ),
                6, 10
        ));
        return arguments.stream();
    }

}
