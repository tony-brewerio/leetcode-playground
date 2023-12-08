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

class LeetCode606Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode606Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestTree2str")
    @ParameterizedTest
    void testTree2str(String expected, LeetCode606.TreeNode root) {
        var result = new LeetCode606().tree2str(root);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestTree2str() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(
                "1(2(4))(3)",
                new LeetCode606.TreeNode(
                        1,
                        new LeetCode606.TreeNode(
                                2,
                                new LeetCode606.TreeNode(4),
                                null
                        ),
                        new LeetCode606.TreeNode(3)
                )
        ));
        arguments.add(Arguments.of(
                "1(2()(4))(3)",
                new LeetCode606.TreeNode(
                        1,
                        new LeetCode606.TreeNode(
                                2,
                                null,
                                new LeetCode606.TreeNode(4)
                        ),
                        new LeetCode606.TreeNode(3)
                )
        ));
        return arguments.stream();
    }
}
