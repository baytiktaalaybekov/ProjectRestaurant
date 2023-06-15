package peaksoft.service.Impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import peaksoft.exception.NotFoundException;
import peaksoft.repository.RestaurantRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.UserService;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
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

    @PostConstruct
    public void init() {
        User user = new User();
        user.setFirstName("Baytik");
        user.setLastName("Taalaybekov");
        user.setEmail("admin@gmail.com");
        user.setDateOfBirth(ZonedDateTime.parse("2002-06-16"));
        user.setExperience(5);
        user.setPhoneNumber("+996707255474");
        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode("admin01"));
        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
        }
    }


    @Override
    public SimpleResponse save(UserRequest userRequest) {
//        Restaurant restaurant = restaurantRepository.findById(userRequest.restaurantId()).orElseThrow(
//                () -> new NotFoundException("Restaurant with id: " + userRequest.restaurantId() + " Not Found"));
//        if (restaurant.)
        return null;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return null;
    }

    @Override
    public UserResponse getUserResponseById(Long id) {
        return null;
    }

    @Override
    public SimpleResponse update(Long id, UserRequest userRequest) {
        return null;
    }

    @Override
    public SimpleResponse deleteUser(Long Id) {
        return null;
    }


}
