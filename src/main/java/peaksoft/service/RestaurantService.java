package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.pagination.PaginationMenuItemResponse;
import peaksoft.dto.pagination.PaginationRestaurantResponse;
import peaksoft.dto.restaurant.restaurantRequest.RestaurantRequest;
import peaksoft.dto.restaurant.restaurantResponse.RestaurantDetailsResponse;
import peaksoft.dto.restaurant.restaurantResponse.RestaurantResponse;

import java.util.List;

public interface RestaurantService {

    SimpleResponse saveRestaurant(RestaurantRequest restaurantRequest);

    List<RestaurantResponse> getAllRestaurantResponse();

    RestaurantResponse getRestaurantResponseById(Long restaurantId);

    SimpleResponse updateRestaurant(Long Id, RestaurantRequest restaurantRequest);

    SimpleResponse deleteRestaurantRequest(Long id);

    List<RestaurantDetailsResponse> countUser(Long id);
}

