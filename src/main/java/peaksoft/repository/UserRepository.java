package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.restaurant.restaurantResponse.RestaurantDetailsResponse;
import peaksoft.dto.user.userResponse.UserResponse;
import peaksoft.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);


    boolean existsByEmail(String email);


//    @Query("select new peaksoft.dto.user.userResponse.UserResponse(u.id,u.firstName,u.lastName,u.dateOfBirth,u.role)from User u")
//    Page<UserResponse> getAllUsers(Pageable pageable);

    @Query("select new peaksoft.dto.user.userResponse.UserResponse(u.id,u.firstName,u.lastName,u.dateOfBirth,u.role)from User u where u.id=:id")
    Optional<UserResponse> getUserResponseById(Long id);

    @Query("select new peaksoft.dto.user.userResponse.UserResponse(u.id,u.firstName,u.lastName,u.dateOfBirth,u.role)from User u")
    List<UserResponse> getAllUsers();


}