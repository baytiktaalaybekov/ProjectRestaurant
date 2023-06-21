package peaksoft.service.Impl;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.pagination.PaginationRestaurantResponse;
import peaksoft.dto.pagination.PaginationUserResponse;
import peaksoft.dto.restaurant.restaurantRequest.RestaurantRequest;
import peaksoft.dto.restaurant.restaurantResponse.RestaurantDetailsResponse;
import peaksoft.dto.restaurant.restaurantResponse.RestaurantResponse;
import peaksoft.dto.user.userResponse.UserResponse;
import peaksoft.entity.Restaurant;
import peaksoft.entity.User;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.RestaurantRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.RestaurantService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Override
    public SimpleResponse saveRestaurant(RestaurantRequest restaurantRequest) {
        if (!restaurantRepository.findAll().isEmpty()) {
            throw new ValidationException("You mast save only 1 Restaurant");
        }
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(
                ()->new NotFoundException("User with email: %s not found".formatted(email)));
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantRequest.getName());
        restaurant.setLocation(restaurantRequest.getLocation());
        restaurant.setRestType(restaurantRequest.getRestType());
        restaurant.setService(restaurantRequest.getService());
        restaurant.setNumberOfUsers(restaurantRequest.getNumberOfUsers());
        user.setRestaurant(restaurant);
        restaurantRepository.save(restaurant);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Restaurant with name : %s successfully saved ...!",
                        restaurantRequest.getName()))
                .build();
    }

    @Override
    public PaginationRestaurantResponse getAllRestaurantResponse(int pageSize,int currentPage) {
        Pageable pageable= PageRequest.of(currentPage-1,pageSize);
        Page<RestaurantResponse> allUsers=restaurantRepository.getAllRestaurantResponse(pageable);
        return PaginationRestaurantResponse
                .builder()
                .users(allUsers.getContent())
                .page(allUsers.getNumber()+1)
                .size(allUsers.getTotalPages())
                .build();
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

    @Override
    public List<RestaurantDetailsResponse> countUser(Long id) {
        return restaurantRepository.countUser(id);
    }
}
