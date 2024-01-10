package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.playground.leetcode.LeetCode2385.TreeNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode2385Test {

    @MethodSource("argumentsForTestAmountOfTime")
    @ParameterizedTest
    void testAmountOfTime(int expected, TreeNode root, int start) throws InterruptedException {
        var result = new LeetCode2385().amountOfTime(root, start);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestAmountOfTime() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(
                4,
                new TreeNode(
                        1,
                        new TreeNode(
                                5,
                                null,
                                new TreeNode(
                                        4,
                                        new TreeNode(9),
                                        new TreeNode(2)
                                )
                        ),
                        new TreeNode(
                                3,
                                new TreeNode(10),
                                new TreeNode(6)
                        )
                ),
                3
        ));
        arguments.add(Arguments.of(
                0,
                new TreeNode(1, null, null),
                1
        ));
        arguments.add(Arguments.of(
                1,
                new TreeNode(2, null, new TreeNode(5)),
                5
        ));
        return arguments.stream();
    }

}
