package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @see <a href="https://leetcode.com/problems/design-a-food-rating-system/submissions/1121846086//">Submission</a>
 * Runtime: 216 ms, Beats 65.54% of users with Java
 * Memory: 74.04 MB, Beats 32.77% of users with Java ( varies greatly between runs )
 * <p>
 * This one was a little bit more complicated than easy ones before.
 */
public class LeetCode2353 {
    private final Logger log = LoggerFactory.getLogger(LeetCode2353.class);

    public FoodRatings foodRatings(String[] foods, String[] cuisines, int[] ratings) {
        return new FoodRatings(foods, cuisines, ratings);
    }

    class FoodRatings {
        private Map<String, TreeMap<Integer, PriorityQueue<String>>> ratingMapByCuisine;
        private Map<String, TreeMap<Integer, PriorityQueue<String>>> ratingMapByFood;
        private Map<String, Integer> foodRatingByFood;

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            ratingMapByCuisine = new HashMap<>();
            ratingMapByFood = new HashMap<>();
            foodRatingByFood = new HashMap<>();
            for (int i = 0; i < foods.length; i++) {
                String food = foods[i];
                String cuisine = cuisines[i];
                int rating = ratings[i];
                var map = ratingMapByCuisine.computeIfAbsent(cuisine, c -> new TreeMap<>());
                map.computeIfAbsent(-1 * rating, r -> new PriorityQueue<>()).add(food);
                ratingMapByFood.put(food, map);
                foodRatingByFood.put(food, -1 * rating);
            }
        }

        public void changeRating(String food, int newRating) {
            var map = ratingMapByFood.get(food);
            var prevRating = foodRatingByFood.get(food);
            var prevQueue = map.get(prevRating);
            prevQueue.remove(food);
            if (prevQueue.isEmpty()) {
                map.remove(prevRating);
            }
            var nextQueue = map.computeIfAbsent(-1 * newRating, r -> new PriorityQueue<>());
            nextQueue.add(food);
            foodRatingByFood.put(food, -1 * newRating);
        }

        public String highestRated(String cuisine) {
            for (PriorityQueue<String> queue : ratingMapByCuisine.get(cuisine).values()) {
                for (String food : queue) {
                    return food;
                }
            }
            return null;
        }
    }
}
