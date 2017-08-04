package com.demo.pizzame.util;

import com.demo.pizzame.model.PizzaPlace;
import com.demo.pizzame.model.Rating;

public class MockUtils {
    public static PizzaPlace createMockPizzaPlace() {
        final PizzaPlace pizzaPlace = new PizzaPlace();
        pizzaPlace.setTitle("pizza hut");
        pizzaPlace.setAddress("randolph");
        pizzaPlace.setPhone("3342");
        pizzaPlace.setDistance("1.0");
        pizzaPlace.setLatitude("1");
        pizzaPlace.setLongitude("1");
        pizzaPlace.setBusinessClickUrl("url");
        Rating rating = new Rating();
        rating.setAverageRating("1");
        pizzaPlace.setRating(rating);
        return pizzaPlace;
    }

    public static PizzaPlace createEmptyMockPizzaPlace() {
        return new PizzaPlace();
    }
}
