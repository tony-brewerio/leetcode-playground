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

class LeetCode2147Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode2147Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestNumberOfWays")
    @ParameterizedTest
    void testNumberOfWays(int expected, String corridor) {
        var result = new LeetCode2147().numberOfWays(corridor);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argumentsForTestNumberOfWays() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(3, "SSPPSPS"));
        arguments.add(Arguments.of(1, "PPSPSP"));
        arguments.add(Arguments.of(0, "S"));
        arguments.add(Arguments.of(0, "SPPPPPPPSPPPSPSSSPPPPPPPPPPPPPPPPPSPPPPPPPPPPPPPPPPSPPPPPSPSPPPPPPSPSPPSPSPPPSPSPPSSPPPPPSPPSSPP"));
        arguments.add(Arguments.of(919999993, "PPPPPSPPSPPSPPPSPPPPSPPPPSPPPPSPPSPPPSPSPPPSPSPPPSPSPPPSPSPPPPSPPPPSPPPSPPSPPPPSPSPPPPSPSPPPPSPSPPPSPPSPPPPSPSPSS"));
        arguments.add(Arguments.of(18335643, "PPPPPPPSPPPSPPPPSPPPSPPPPPSPPPSPPSPPSPPPPPSPSPPPPPSPPSPPPPPSPPSPPSPPPSPPPPSPPPPSPPPPPSPSPPPPSPSPPPSPPPPSPPPPPSPSPPSPPPPSPPSPPSPPSPPPSPPSPSPPSSSS"));
        return arguments.stream();
    }
}
