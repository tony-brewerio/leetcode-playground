package org.playground.leetcode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

class LeetCode2642Test {
    private static final Logger log = LoggerFactory.getLogger(LeetCode2642Test.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MethodSource("argumentsForTestGraph")
    @ParameterizedTest
    void testGraph(int n, List<Object> ops) throws InterruptedException {
        var graph = new LeetCode2642.Graph(n, new int[0][0]);
        for (Object op : ops) {
            long start = System.nanoTime();
            if (op instanceof GraphInitOp graphInitOp) {
                var edges = new ArrayList<int[]>();
                for (GraphAddEdgeOp edge : graphInitOp.edges()) {
                    edges.add(new int[]{edge.n1(), edge.n2(), edge.cost()});
                }
                graph = new LeetCode2642.Graph(graphInitOp.n(), edges.toArray(new int[0][0]));
                log.info(
                        "op - GraphInitOp - n:{} - edges:{} - {} ms",
                        graphInitOp.n(),
                        graphInitOp.edges().size(),
                        TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start)
                );
            }
            if (op instanceof GraphAddEdgeOp graphAddEdgeOp) {
                graph.addEdge(new int[]{graphAddEdgeOp.n1(), graphAddEdgeOp.n2(), graphAddEdgeOp.cost()});
                log.info(
                        "op - GraphAddEdgeOp - n1:{} - n2:{} - cost:{} - {} ms",
                        graphAddEdgeOp.n1(),
                        graphAddEdgeOp.n2(),
                        graphAddEdgeOp.cost(),
                        TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start)
                );
            }
            if (op instanceof GraphShortestPathOp graphShortestPathOp) {
                assertThat(graph.shortestPath(graphShortestPathOp.n1(), graphShortestPathOp.n2())).isEqualTo(graphShortestPathOp.expected());
                log.info(
                        "op - GraphShortestPathOp - n1:{} - n2:{} - expected:{} - {} ms",
                        graphShortestPathOp.n1(),
                        graphShortestPathOp.n2(),
                        graphShortestPathOp.expected(),
                        TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start)
                );
            }
            if (op instanceof GraphShortestPathNoAssertOp graphShortestPathNoAssertOp) {
                if (!(graphShortestPathNoAssertOp.n1() == 9 && graphShortestPathNoAssertOp.n2() == 14)) {
                    continue;
                }
                for (int i = 0; i < 10; i++) {
                    int cost = graph.shortestPath(graphShortestPathNoAssertOp.n1(), graphShortestPathNoAssertOp.n2());
                    log.info(
                            "op - GraphShortestPathNoAssertOp - n1:{} - n2:{} - cost:{} - {} ms",
                            graphShortestPathNoAssertOp.n1(),
                            graphShortestPathNoAssertOp.n2(),
                            cost,
                            TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start)
                    );
                    start = System.nanoTime();
                }
            }
        }
    }

    private static Stream<Arguments> argumentsForTestGraph() throws IOException {
        var arguments = new ArrayList<Arguments>();
        arguments.add(Arguments.of(4, List.of(
                new GraphAddEdgeOp(0, 2, 5),
                new GraphAddEdgeOp(0, 1, 2),
                new GraphAddEdgeOp(1, 2, 1),
                new GraphAddEdgeOp(3, 0, 3),
                new GraphShortestPathOp(3, 2, 6),
                new GraphShortestPathOp(0, 3, -1),
                new GraphAddEdgeOp(1, 3, 4),
                new GraphShortestPathOp(0, 3, 6)
        )));
        arguments.add(argumentsFromLeetcode(
                """
                        ["Graph","shortestPath","addEdge","shortestPath","addEdge","addEdge","shortestPath","addEdge","addEdge","shortestPath","addEdge","addEdge","addEdge","addEdge","shortestPath","addEdge","addEdge","shortestPath","addEdge","shortestPath","addEdge","addEdge","addEdge","addEdge","addEdge","addEdge","shortestPath","shortestPath","addEdge","shortestPath","shortestPath","addEdge","shortestPath","shortestPath","shortestPath","shortestPath","addEdge","shortestPath","addEdge","shortestPath","addEdge","shortestPath","shortestPath","shortestPath","shortestPath","addEdge","shortestPath","shortestPath","shortestPath","shortestPath","shortestPath","shortestPath","addEdge","shortestPath","addEdge","addEdge","shortestPath","shortestPath","shortestPath","shortestPath","addEdge","addEdge","shortestPath","addEdge","shortestPath","addEdge","addEdge","shortestPath","shortestPath","shortestPath","addEdge","addEdge","addEdge","addEdge","addEdge","addEdge","addEdge","addEdge","shortestPath","shortestPath","shortestPath","addEdge","addEdge","addEdge","addEdge","addEdge","shortestPath","addEdge","addEdge","shortestPath","addEdge","addEdge","shortestPath","shortestPath","addEdge","addEdge","addEdge","addEdge","addEdge","addEdge","addEdge","addEdge","addEdge","shortestPath","shortestPath","addEdge","shortestPath","addEdge","addEdge","shortestPath","shortestPath","addEdge","addEdge","shortestPath","addEdge","shortestPath","shortestPath","shortestPath","addEdge","addEdge","shortestPath","shortestPath","shortestPath","shortestPath","shortestPath","shortestPath","addEdge","shortestPath","shortestPath","addEdge","addEdge","shortestPath","shortestPath","shortestPath","addEdge","shortestPath","shortestPath","shortestPath","addEdge","addEdge","shortestPath","shortestPath","addEdge","shortestPath","shortestPath","shortestPath","shortestPath","addEdge","addEdge","addEdge","addEdge","addEdge","shortestPath","addEdge","shortestPath","addEdge","addEdge","addEdge","shortestPath","shortestPath","addEdge","shortestPath","addEdge","addEdge","addEdge","addEdge","shortestPath","shortestPath","shortestPath","shortestPath","addEdge","addEdge","addEdge","shortestPath","shortestPath","addEdge","addEdge","shortestPath","addEdge","shortestPath","shortestPath","addEdge","addEdge","shortestPath","addEdge","shortestPath","shortestPath","addEdge","shortestPath","addEdge","addEdge","shortestPath","shortestPath","shortestPath","shortestPath","shortestPath","shortestPath","shortestPath","shortestPath"]
                        [[34,[[14,5,881545],[8,32,345511],[18,16,514472],[0,13,869299],[30,12,570902],[22,9,351959],[27,30,70797],[11,29,368155],[14,27,795439],[0,22,378440],[20,4,685158],[22,24,362991],[26,22,961749],[15,24,723793],[0,20,757290],[26,12,294164],[24,31,484639],[16,5,850191],[9,4,206147],[30,23,262594],[30,4,49911],[30,5,579739],[31,30,400322],[11,16,933833],[26,18,327176],[5,28,870867],[19,27,894398],[2,18,726786],[12,11,592147],[12,31,545151],[7,29,804100],[28,12,719539],[20,30,987142],[32,33,268418],[18,19,318987],[8,19,660670],[3,30,939003],[4,21,973781],[9,33,725276],[17,15,473781],[31,1,559320],[24,20,854717],[23,0,545468],[32,17,147338],[18,7,876227],[22,11,977085],[5,16,753350],[27,19,475870],[8,23,31731],[0,8,342882],[16,2,47335],[16,9,435913],[27,12,313869],[6,14,676935],[11,33,301787],[7,2,336201],[22,31,9027],[8,0,221060],[13,11,347777],[6,26,264331],[33,23,384371],[3,20,950834],[4,11,589519],[17,30,207246],[1,24,286223],[19,3,703979],[22,3,534258],[23,22,908626],[29,7,89816],[15,23,20997],[2,15,688291],[4,26,969928],[24,4,894539],[33,18,16425],[27,26,157004],[4,1,150614],[1,31,445622],[9,7,823910],[4,32,377445],[28,17,556971],[9,15,480174],[23,29,713662],[5,4,171535],[15,7,914953],[32,26,100454],[10,8,786418],[33,9,763480],[12,9,299566],[3,5,326034],[30,9,858942],[19,4,905936],[24,17,464421],[21,11,291698],[1,0,745277],[19,6,930175],[23,24,546620],[15,20,929614],[7,12,254575],[7,28,308200],[24,22,113972],[12,0,903325],[23,32,869115],[29,3,669651],[26,16,45027],[2,24,737715],[33,3,965036],[5,27,827542],[31,8,328181],[31,10,6105],[16,15,173616],[3,27,73278],[31,29,4698],[19,33,925349],[12,15,333520],[28,8,216735],[4,23,867583]]],[29,7],[[27,24,1564]],[5,1],[[26,19,921788]],[[11,1,1557]],[15,29],[[22,12,953203]],[[30,27,289]],[26,23],[[29,4,449122]],[[2,8,160]],[[0,19,116]],[[14,16,42]],[7,12],[[17,12,111415]],[[12,21,32]],[0,7],[[10,22,719580]],[0,11],[[4,27,12]],[[12,25,2]],[[16,11,1]],[[8,15,1]],[[18,3,1]],[[17,4,1]],[28,17],[16,8],[[23,18,511986]],[18,0],[19,26],[[28,23,1]],[28,17],[3,21],[26,1],[5,22],[[21,19,1]],[9,22],[[19,29,691745]],[32,24],[[7,32,1]],[26,33],[6,12],[15,5],[31,18],[[4,10,110834]],[11,5],[24,25],[15,24],[19,21],[11,32],[20,22],[[29,5,1]],[28,10],[[26,7,336849]],[[32,13,1]],[0,5],[33,18],[7,20],[8,7],[[23,13,460444]],[[30,11,757203]],[1,8],[[3,19,1]],[29,11],[[6,16,600365]],[[18,12,1]],[5,5],[9,16],[4,32],[[1,3,1]],[[9,21,243252]],[[7,17,34236]],[[24,21,932556]],[[10,31,1]],[[5,31,667818]],[[6,18,1]],[[26,32,1]],[22,24],[9,21],[23,19],[[11,12,1]],[[31,19,1]],[[19,7,493450]],[[23,21,368140]],[[25,33,1]],[28,0],[[14,13,1]],[[23,12,1]],[17,18],[[1,13,258165]],[[5,24,1]],[19,22],[18,3],[[26,25,6450]],[[5,7,1]],[[5,0,1]],[[33,0,1]],[[18,4,856222]],[[31,11,703928]],[[25,3,454527]],[[5,12,1]],[[1,11,544425]],[0,28],[31,19],[[20,13,266912]],[5,4],[[27,16,885728]],[[14,4,1]],[9,20],[20,12],[[0,11,1]],[[1,33,1]],[12,29],[[13,17,725739]],[10,32],[10,15],[9,32],[[32,16,182529]],[[30,16,1]],[27,33],[19,0],[25,0],[11,27],[23,14],[26,25],[[26,4,1]],[21,3],[7,0],[[24,12,1]],[[2,1,481101]],[15,3],[13,15],[26,22],[[30,25,77291]],[23,9],[14,14],[24,0],[[29,16,317440]],[[10,18,578376]],[23,15],[11,1],[[26,28,1]],[4,4],[8,22],[22,4],[1,26],[[20,9,1]],[[21,7,1]],[[5,9,672759]],[[7,11,482010]],[[25,32,1]],[9,14],[[31,2,1]],[6,1],[[2,0,615622]],[[26,13,1]],[[7,23,339666]],[30,11],[12,26],[[32,15,1]],[8,6],[[1,19,730210]],[[6,7,1]],[[24,26,556317]],[[5,29,1]],[31,28],[14,32],[31,29],[24,0],[[6,29,1]],[[10,19,1]],[[6,27,418134]],[26,2],[9,23],[[32,31,304291]],[[25,10,1]],[25,30],[[2,29,1]],[14,31],[8,7],[[20,14,968364]],[[9,6,1]],[4,3],[[1,10,178656]],[20,14],[1,27],[[12,22,1]],[18,27],[[16,22,276020]],[[26,33,1]],[31,10],[11,6],[10,26],[2,22],[20,22],[26,11],[3,28],[32,33]]
                        [null,89816,null,322149,null,null,695314,null,null,239640,null,null,null,null,254575,null,null,481981,null,1028286,null,null,null,null,null,null,556971,47495,null,543865,934261,null,556971,387179,46585,287083,null,321695,null,148915,null,346815,558495,859018,379358,null,644247,472090,567617,1036168,457972,800706,null,667806,null,null,392166,16425,1003633,703041,null,null,696713,null,235299,null,null,0,408190,219090,null,null,null,null,null,null,null,null,139354,243252,511988,null,null,null,null,null,437795,null,null,127843,null,null,643235,1,null,null,null,null,null,null,null,null,null,700367,1,null,34238,null,null,1062440,614690,null,null,204102,null,4702,197679,321683,null,null,70803,598503,2,74836,1607144,6450,null,293256,105053,null,null,22561,540907,115549,null,299567,0,5,null,null,193136,1557,null,0,211376,47964,230283,null,null,null,null,null,1814867,null,1564,null,null,null,2,100457,null,951207,null,null,null,null,105156,47,4698,5,null,null,null,92362,228725,null,null,105083,null,48,21007,null,null,3140,null,676937,34261,null,34258,null,null,7,299568,100460,4,5,3,175301,21002]
                        """
        ));
        return arguments.stream();
    }

    private static Arguments argumentsFromLeetcode(String text) throws IOException {
        var parser = objectMapper.createParser(text);
        var ops = new ArrayList<>();
        if (parser.readValueAsTree() instanceof ArrayNode commands
                && parser.readValueAsTree() instanceof ArrayNode args
                && parser.readValueAsTree() instanceof ArrayNode expected) {
            for (int i = 0; i < commands.size(); i++) {
                switch (commands.get(i).textValue()) {
                    case "Graph" -> {
                        if (args.get(i) instanceof ArrayNode arg1 && arg1.get(1) instanceof ArrayNode arg2) {
                            ops.add(new GraphInitOp(
                                    arg1.get(0).asInt(),
                                    StreamSupport.stream(arg2.spliterator(), false)
                                            .map(arg3 -> (ArrayNode) arg3)
                                            .map(GraphAddEdgeOp::of)
                                            .toList()
                            ));
                        }
                    }
                    case "addEdge" -> {
                        if (args.get(i) instanceof ArrayNode arg1 && arg1.get(0) instanceof ArrayNode arg2) {
                            ops.add(GraphAddEdgeOp.of(arg2));
                        }
                    }
                    case "shortestPath" -> {
                        if (args.get(i) instanceof ArrayNode arg1) {
                            ops.add(new GraphShortestPathOp(arg1.get(0).asInt(), arg1.get(1).asInt(), expected.get(i).asInt()));
                        }
                    }
                }
            }
        }
        return Arguments.of(0, ops);
    }

    private record GraphInitOp(int n, List<GraphAddEdgeOp> edges) {
    }

    private record GraphAddEdgeOp(int n1, int n2, int cost) {
        private static GraphAddEdgeOp of(ArrayNode arr) {
            return new GraphAddEdgeOp(arr.get(0).asInt(), arr.get(1).asInt(), arr.get(2).asInt());
        }
    }

    private record GraphShortestPathOp(int n1, int n2, int expected) {
    }

    private record GraphShortestPathNoAssertOp(int n1, int n2) {
    }

}
