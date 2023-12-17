package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @see <a href="https://leetcode.com/problems/design-a-food-rating-system/submissions/1121916196/">Submission</a>
 * Runtime: 211 ms, Beats 69.49% of users with Java
 * Memory: 74.90 MB, Beats 23.73% of users with Java ( varies greatly between runs )
 * <p>
 * This one was a little bit more complicated than easy ones before.
 */
public class LeetCode2353 {
    private final Logger log = LoggerFactory.getLogger(LeetCode2353.class);

    public FoodRatings foodRatings(String[] foods, String[] cuisines, int[] ratings) {
        return new FoodRatings(foods, cuisines, ratings);
    }

    class FoodRatings {
        private Map<String, FoodRatingMap> ratingMapByCuisine;
        private Map<String, FoodRatingMap> ratingMapByFood;

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            ratingMapByCuisine = new HashMap<>();
            ratingMapByFood = new HashMap<>(foods.length);
            for (int i = 0; i < foods.length; i++) {
                String food = foods[i];
                String cuisine = cuisines[i];
                int rating = ratings[i];
                var map = ratingMapByCuisine.computeIfAbsent(cuisine, c -> new FoodRatingMap());
                map.add(food, rating);
                ratingMapByFood.put(food, map);
            }
        }

        public void changeRating(String food, int rating) {
            var map = ratingMapByFood.get(food);
            map.change(food, rating);
        }

        public String highestRated(String cuisine) {
            return ratingMapByCuisine.get(cuisine).highest();
        }

        public static class FoodRatingMap {
            private TreeMap<Integer, TreeSet<String>> foodsByRating = new TreeMap<>();
            private Map<String, Integer> ratingByFood = new HashMap<>();

            public void add(String food, int rating) {
                var map = foodsByRating.computeIfAbsent(rating, r -> new TreeSet<>());
                map.add(food);
                ratingByFood.put(food, rating);
            }

            public void change(String food, int rating) {
                var prevRating = ratingByFood.put(food, rating);
                var prevSet = foodsByRating.get(prevRating);
                prevSet.remove(food);
                if (prevSet.isEmpty()) {
                    foodsByRating.remove(prevRating);
                }
                var map = foodsByRating.computeIfAbsent(rating, r -> new TreeSet<>());
                map.add(food);
            }

            public String highest() {
                return foodsByRating.lastEntry().getValue().first();
            }
        }
    }
}
