package peaksoft.service.Impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.jwt.JwtUtil;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.user.userRequest.UserRequest;
import peaksoft.dto.user.userResponse.UserResponse;
import peaksoft.dto.user.userResponse.UserResponses;
import peaksoft.entity.Restaurant;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.exception.BadRequestException;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.RestaurantRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.UserService;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSerImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    private final RestaurantRepository restaurantRepository;


    @Override
    public UserResponses authenticate(UserRequest userRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.email(),
                userRequest.password()));

        User user = userRepository.findByEmail(userRequest.email
                ()).orElseThrow(() -> new NoSuchElementException(String.format
                ("User with email: %s doesn't exists", userRequest.email())));

        String token = jwtUtil.generatorToken(user);

        return UserResponses.builder().email(user.getEmail()).token(token).build();


    }

    @Override
    public SimpleResponse register(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.email())) {
            return SimpleResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(String.format("User with email: %s already exists", userRequest.email()))
                    .build();
        }
        if (userRequest.role().equals(Role.ADMIN)) {
            return SimpleResponse.builder()
                    .status(HttpStatus.FORBIDDEN)
                    .message("Forbidden")
                    .build();
        }
        if (userRequest.role().equals(Role.CHEF)) {
            Period between = Period.between(userRequest.dateOfBirth(), LocalDate.now());
            if (between.getYears() > 45 || between.getYears() < 25) {
                return SimpleResponse.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message("The cook must be between 25 and 45 years of age")
                        .build();
            }
        }
        if (userRequest.role().equals(Role.WAITER)) {
            Period bet = Period.between(userRequest.dateOfBirth(), LocalDate.now());
            if (bet.getYears() > 30 || bet.getYears() < 18) {
                return SimpleResponse.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message("Waiter must be between 18 and 30 years of age")
                        .build();
            }
        }
        if (userRequest.role().equals(Role.WAITER)) {
            if (userRequest.experience() < 1 || userRequest.experience() > 10) {
                return SimpleResponse.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message("Waiter experience must be between 1 and 10")
                        .build();
            }
        }
        Restaurant restaurant = restaurantRepository.findById(1L).orElseThrow(() -> new NotFoundException("Not"));
        var count = restaurant.getUsers().size();
        if (count > 15) {
            throw new BadRequestException("Not vacancy");
        }
        User user = new User();
        userMapToResponse(userRequest, user);
        userRepository.save(user);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Successfully saved")
                .build();
    }


    @PostConstruct
    public void init() {
        User user = new User();
        user.setFirstName("Baytik");
        user.setLastName("Taalaybekov");
        user.setEmail("admin@gmail.com");
        user.setDateOfBirth(LocalDate.parse("2002-06-16"));
        user.setExperience(5);
        user.setPhoneNumber("+996707255474");
        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode("admin"));
        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
        }
    }


    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public UserResponse getUserResponseById(Long id) {
        return userRepository.getUserResponseById(id).orElseThrow(() ->
                new NotFoundException("User with id: " + id + " not found "));
    }

    @Override
    public SimpleResponse update(Long id, UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.email())) {
            return SimpleResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(String.format("User with email: %s already exists", userRequest.email()))
                    .build();
        }
        User user = jwtUtil.getAuthentication();
        User user1 = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id: " + id + " is not found!"));
        if (user.getRole().equals(Role.ADMIN)) {
            if (userRepository.existsById(id)) {
                userMapToResponse(userRequest, user);
                userRepository.save(user);
                return SimpleResponse.builder()
                        .status(HttpStatus.OK)
                        .message("Successfully updated")
                        .build();
            } else throw new NotFoundException("User with id:" + id + " is does not exist...");
        } else {
            if (user.equals(user1)) {
                if (userRepository.existsById(id)) {
                    userMapToResponse(userRequest, user);
                    userRepository.save(user);
                }
            }
        }

        return null;
    }


    @Override
    public SimpleResponse deleteUser(Long Id) {
        userRepository.deleteById(Id);
        return SimpleResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message("User with id : " + Id + " doesn't exist")
                .build();
    }

    @Override
    public SimpleResponse answer(Long restaurantId, Long userId, String word) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new NotFoundException("Restaurant with id: " + restaurantId + " is not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User with id: " + userId + " is not found"));
        if (word.startsWith("accepted")) {
            user.setRestaurant(restaurant);
            restaurant.getUsers().add(user);
            userRepository.save(user);
            restaurantRepository.save(restaurant);
            return SimpleResponse.builder()
                    .status(HttpStatus.OK).message("Successfully saved").build();
        } else if (word.startsWith("rejected")) {
            userRepository.delete(user);
            return SimpleResponse.builder().status(HttpStatus.OK).message("Successfully deleted").build();

        } else {
            return SimpleResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("Write correctly!!!")
                    .build();
        }
    }


    private void userMapToResponse(UserRequest userRequest, User user1) {
        user1.setFirstName(userRequest.firstName());
        user1.setLastName(userRequest.lastName());
        user1.setDateOfBirth(userRequest.dateOfBirth());
        user1.setEmail(userRequest.email());
        user1.setPassword(passwordEncoder.encode(userRequest.password()));
        user1.setExperience(userRequest.experience());
        user1.setPhoneNumber(userRequest.phoneNumber());
        user1.setRole(userRequest.role());
        userRepository.save(user1);
    }
}



