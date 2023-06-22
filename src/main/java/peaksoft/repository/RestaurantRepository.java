package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.restaurant.restaurantResponse.RestaurantDetailsResponse;
import peaksoft.dto.restaurant.restaurantResponse.RestaurantResponse;
import peaksoft.entity.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Boolean existsByName(String name);

//    @Query("select new peaksoft.dto.restaurant.restaurantResponse.RestaurantResponse(r.id,r.name,r.location)from Restaurant r")
//    Page<RestaurantResponse> getAllRestaurantResponse(Pageable pageable);
    @Query("select new peaksoft.dto.restaurant.restaurantResponse.RestaurantResponse(r.id,r.name,r.location)from Restaurant r")
    List<RestaurantResponse> getAllRestaurantResponse();

    @Query("select new peaksoft.dto.restaurant.restaurantResponse.RestaurantResponse(r.id,r.name,r.location)from Restaurant r where r.id= ?1")
    Optional<RestaurantResponse> getRestaurantResponseById(Long restaurantId);

    @Query("SELECT NEW peaksoft.dto.restaurant.restaurantResponse.RestaurantDetailsResponse(r.id, COUNT(u)) " +
            "FROM Restaurant r " +
            "JOIN r.users u " +
            "WHERE r.id = ?1 and u.role <> ('ADMIN') " +
            "GROUP BY r.id, r.name")
    List<RestaurantDetailsResponse> countUser(Long id);

    Optional<Restaurant> findRestaurantByName(String name);

}
