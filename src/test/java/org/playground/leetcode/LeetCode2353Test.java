package org.playground.leetcode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.playground.leetcode.helpers.AveragingOperationTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode2353Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode2353Test.class);
    private static final AveragingOperationTimer timer = new AveragingOperationTimer(LeetCode2353Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestFoodRatings")
    @ParameterizedTest
    void testFoodRatings(TestRunArguments arguments) {
        var ratings = new LeetCode2353().foodRatings(arguments.foods(), arguments.cuisines(), arguments.ratings());
        for (Object op : arguments.ops()) {
            if (op instanceof TestRunOpCheck check) {
                String food = ratings.highestRated(check.cuisine());
                assertThat(food).isEqualTo(check.food());
            }
            if (op instanceof TestRunOpChange change) {
                ratings.changeRating(change.food(), change.rating());
            }
        }
    }

    @CsvFileSource(resources = "/LeetCode2353Test.argumentsForTestFoodRatings.csv", delimiterString = ";", maxCharsPerColumn = 1_000_000)
    @ParameterizedTest(name = "case #{index}")
    void testFoodRatingsLarge(int index, String line1, String line2) throws IOException {
        var arguments = parseToArguments(line1, line2);
        var ratings = new LeetCode2353().foodRatings(arguments.foods(), arguments.cuisines(), arguments.ratings());
        for (Object op : arguments.ops()) {
            if (op instanceof TestRunOpCheck check) {
                String food = ratings.highestRated(check.cuisine());
                assertThat(food).isEqualTo(check.food());
            }
            if (op instanceof TestRunOpChange change) {
                ratings.changeRating(change.food(), change.rating());
            }
        }
    }

    private static Stream<Arguments> argumentsForTestFoodRatings() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(parseToArguments(
                """
                        [[["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]], ["korean"], ["japanese"], ["sushi", 16], ["japanese"], ["ramen", 16], ["japanese"]]
                        [null, "kimchi", "ramen", null, "sushi", null, "ramen"]
                        """)));
        return arguments.stream();
    }

    private static TestRunArguments parseToArguments(String str) throws IOException {
        String[] lines = str.strip().split("\n");
        return parseToArguments(lines[0], lines[1]);
    }

    private static TestRunArguments parseToArguments(String line1, String line2) throws IOException {
        var tree = objectMapper.readTree(line1);
        var checks = objectMapper.readValue(line2, arrayOfString);
        var foods = objectMapper.readValue(objectMapper.treeAsTokens(tree.get(0).get(0)), arrayOfString);
        var cuisines = objectMapper.readValue(objectMapper.treeAsTokens(tree.get(0).get(1)), arrayOfString);
        var ratings = objectMapper.readValue(objectMapper.treeAsTokens(tree.get(0).get(2)), arrayOfInt);
        List<Object> ops = new ArrayList<>();
        for (int i = 1; i < tree.size(); i++) {
            var t = tree.get(i);
            if (t.size() == 1) {
                ops.add(new TestRunOpCheck(t.get(0).asText(), checks.length == 0 ? "" : checks[i]));
            } else {
                ops.add(new TestRunOpChange(t.get(0).asText(), t.get(1).asInt()));
            }
        }
        return new TestRunArguments(foods, cuisines, ratings, ops);
    }

    record TestRunArguments(String[] foods, String[] cuisines, int[] ratings, List<Object> ops) {
    }

    record TestRunOpCheck(String cuisine, String food) {
    }

    record TestRunOpChange(String food, int rating) {
    }

    private static final TypeReference<String[]> arrayOfString = new TypeReference<>() {
    };
    private static final TypeReference<int[]> arrayOfInt = new TypeReference<>() {
    };
}
