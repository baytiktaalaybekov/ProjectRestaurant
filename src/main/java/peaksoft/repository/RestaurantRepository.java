package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.restaurant.restaurantResponse.RestaurantResponse;
import peaksoft.entity.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Boolean existsByName(String name);

    @Query("select new peaksoft.dto.restaurant.restaurantResponse.RestaurantResponse(r.id,r.name,r.location)from Restaurant r")
    List<RestaurantResponse> getAllRestaurantResponse();

    @Query("select new peaksoft.dto.restaurant.restaurantResponse.RestaurantResponse(r.id,r.name,r.location)from Restaurant r where r.id= ?1")
    Optional<RestaurantResponse> getRestaurantResponseById(Long restaurantId);
}