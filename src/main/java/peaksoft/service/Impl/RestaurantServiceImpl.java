package peaksoft.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.restaurant.restaurantRequest.RestaurantRequest;
import peaksoft.dto.restaurant.restaurantResponse.RestaurantResponse;
import peaksoft.entity.Restaurant;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.RestaurantRepository;
import peaksoft.service.RestaurantService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public SimpleResponse saveRestaurant(RestaurantRequest restaurantRequest) {
        if (restaurantRepository.existsByName(restaurantRequest.getName())) {
            return SimpleResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(String.format("Restaurant with name : %s already exists",
                            restaurantRequest.getName()))
                    .build();
        }
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantRequest.getName());
        restaurant.setLocation(restaurantRequest.getLocation());
        restaurant.setRestType(restaurantRequest.getRestType());
        restaurant.setService(restaurantRequest.getService());
        restaurantRepository.save(restaurant);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Restaurant with name : %s successfully saved ...!",
                        restaurantRequest.getName()))
                .build();
    }

    @Override
    public List<RestaurantResponse> getAllRestaurantResponse() {
        return restaurantRepository.getAllRestaurantResponse();
    }

    @Override
    public RestaurantResponse getRestaurantResponseById(Long restaurantId) {
        return restaurantRepository.getRestaurantResponseById(restaurantId).orElseThrow(
                ()->new NotFoundException("Restaurant with id: "+restaurantId+" Not found")
        );
    }

    @Override
    public SimpleResponse updateRestaurant(Long Id, RestaurantRequest restaurantRequest) {
       Restaurant restaurant1 = restaurantRepository.findById(Id).orElseThrow(
                ()->new NotFoundException("Restaurant with id: " +Id+ " Not found"));
       restaurant1.setName(restaurantRequest.getName());
       restaurant1.setLocation(restaurantRequest.getLocation());
       restaurant1.setRestType(restaurantRequest.getRestType());
       restaurant1.setService(restaurantRequest.getService());
       restaurantRepository.save(restaurant1);
        return SimpleResponse
                .builder()
                .status(HttpStatus.OK)
                .message("Update Restaurant ")
                .build();
    }

    @Override
    public SimpleResponse deleteRestaurantRequest(Long id) {
        restaurantRepository.deleteById(id);
        return SimpleResponse
                .builder()
                .status(HttpStatus.OK)
                .message("Delete Restaurant ")
                .build();
    }
}
