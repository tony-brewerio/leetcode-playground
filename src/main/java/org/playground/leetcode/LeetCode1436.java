package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/destination-city/submissions/1120559410/">Submission</a>
 * Runtime: 2 ms, Beats 79.52% of users with Java
 * Memory: 42.65 MB, Beats 85.46% of users with Java ( varies greatly between runs )
 * <p>
 * Build a graph, follow it.
 * Since we are guaranteed to not have any cycles or dead ends, we can pick any element to start with.
 */
public class LeetCode1436 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1436.class);

    public String destCity(List<List<String>> paths) {
        var routes = new HashMap<String, String>(paths.size());
        for (List<String> path : paths) {
            routes.put(path.get(0), path.get(1));
        }
        String city = routes.keySet().iterator().next();
        while (true) {
            String next = routes.get(city);
            if (next == null) {
                return city;
            } else {
                city = next;
            }
        }
    }
}
