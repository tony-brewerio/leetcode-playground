package org.playground.leetcode;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.playground.leetcode.helpers.AveragingOperationTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode2349Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode2349Test.class);
    private static final AveragingOperationTimer timer = new AveragingOperationTimer(LeetCode2349Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestNumberContainers")
    @ParameterizedTest(name = "case #{index}")
    void testNumberContainers(List<Object> operations) {
        var nc = new LeetCode2349().numberContainers();
        for (Object operation : operations) {
            if (operation instanceof TestRunOpChange change) {
                nc.change(change.index(), change.number());
            } else if (operation instanceof TestRunOpCheck check) {
                int result = nc.find(check.number());
                assertThat(result).isEqualTo(check.index());
            }
        }
    }

    private static Stream<Arguments> argumentsForTestNumberContainers() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(parseToArguments(
                """
                        [[], [10], [2, 10], [1, 10], [3, 10], [5, 10], [10], [1, 20], [10]]
                        [null, -1, null, null, null, null, 1, null, 2]
                        """)));
        return arguments.stream();
    }

    private static List<Object> parseToArguments(String str) throws IOException {
        String[] lines = str.strip().split("\n");
        return parseToArguments(lines[0], lines[1]);
    }

    private static List<Object> parseToArguments(String line1, String line2) throws IOException {
        var items = objectMapper.readValue(line1, arrayOfArrayOfInt);
        var checks = objectMapper.readValue(line2, arrayOfInt);
        List<Object> operations = new ArrayList<>();
        for (int i = 1; i < items.length; i++) {
            var item = items[i];
            if (item.length == 2) {
                operations.add(new TestRunOpChange(item[0], item[1]));
            } else {
                operations.add(new TestRunOpCheck(checks[i], item[0]));
            }
        }
        return operations;
    }

    record TestRunOpCheck(int index, int number) {
    }

    record TestRunOpChange(int index, int number) {
    }

    private static final TypeReference<int[]> arrayOfInt = new TypeReference<>() {
    };
    private static final TypeReference<int[][]> arrayOfArrayOfInt = new TypeReference<>() {
    };
}
