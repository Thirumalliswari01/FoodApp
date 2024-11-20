package com.tapfoods.dao;

import java.util.List;
import java.util.Optional;

import com.tapfoods.model.Restaurant;

public interface RestaurantDAO {
    int addRestaurant(Restaurant r);
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurant(int restaurantId);
    int updateRestaurant(Restaurant r);
    int deleteRestaurant(int restaurantId);
}
