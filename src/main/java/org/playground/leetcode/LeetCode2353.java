package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @see <a href="https://leetcode.com/problems/design-a-food-rating-system/submissions/1121966898/">Submission</a>
 * Runtime: 185 ms, Beats 85.31% of users with Java
 * Memory: 72.54 MB, Beats 54.80% of users with Java ( varies greatly between runs )
 * <p>
 * The final variant uses TreeSet and custom comparable record wrapper over food + rating.
 * <p>
 * From what I've seen of other solutions, they usually gain a bit of speed by not removing old TreeSet items.
 * Instead, they update items to mark them as tombstones, and ignore those when iterating over the set from the start.
 * It can work perhaps, but feels off to me, and the current solution is more than good enough anyway.
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
            private TreeSet<FoodWithRating> set = new TreeSet<>();
            private Map<String, FoodWithRating> foodWithRatingByFood = new HashMap<>();

            public void add(String food, int rating) {
                var fwr = new FoodWithRating(food, rating);
                set.add(fwr);
                foodWithRatingByFood.put(food, fwr);
            }

            public void change(String food, int rating) {
                var fwr = new FoodWithRating(food, rating);
                set.remove(foodWithRatingByFood.put(food, fwr));
                set.add(fwr);
            }

            public String highest() {
                return set.first().food;
            }
        }

        public record FoodWithRating(String food, int rating) implements Comparable<FoodWithRating> {
            @Override
            public int compareTo(FoodWithRating o) {
                var rc = Integer.compare(o.rating, rating);
                return rc != 0 ? rc : food.compareTo(o.food);
            }
        }
    }
}
