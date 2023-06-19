package peaksoft.api;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.restaurant.restaurantRequest.RestaurantRequest;
import peaksoft.dto.restaurant.restaurantResponse.RestaurantDetailsResponse;
import peaksoft.dto.restaurant.restaurantResponse.RestaurantResponse;
import peaksoft.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantApi {

    private final RestaurantService restaurantService;

    @GetMapping
    public List<RestaurantResponse> getAllRestaurant(){
        return restaurantService.getAllRestaurantResponse();
    }

    @PermitAll()
    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse saveRestaurant(@RequestBody RestaurantRequest restaurantRequest){
        return restaurantService.saveRestaurant(restaurantRequest);
    }

    @GetMapping("/{restaurantId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public RestaurantResponse getRestaurantById(@PathVariable Long restaurantId){
        return restaurantService.getRestaurantResponseById(restaurantId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{restaurantId}")
    public SimpleResponse updateRestaurant(@PathVariable Long restaurantId,
                                           @RequestBody RestaurantRequest restaurantRequest){
        return restaurantService.updateRestaurant(restaurantId,restaurantRequest);
    }
    @DeleteMapping("/{restaurantId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse deleteRestaurant(@PathVariable Long restaurantId){
        return restaurantService.deleteRestaurantRequest(restaurantId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{restaurantId}/count")
    public List<RestaurantDetailsResponse> sort(@PathVariable Long restaurantId){
        return restaurantService.countUser(restaurantId);
    }

}
