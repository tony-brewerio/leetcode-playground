package org.playground.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode2264Test {

    @MethodSource("argumentsForTestLargestGoodInteger")
    @ParameterizedTest
    void testLargestGoodInteger(String expected, String num) throws InterruptedException {
        var result = new LeetCode2264().largestGoodInteger(num);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestLargestGoodInteger() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of("777", "6777133339"));
        arguments.add(Arguments.of("000", "2300019"));
        arguments.add(Arguments.of("", "42352338"));
        arguments.add(Arguments.of("444", "835184233004443444000244411167948"));
        return arguments.stream();
    }

}
