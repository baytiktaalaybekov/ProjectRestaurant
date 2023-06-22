package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.user.userRequest.UserRequest;
import peaksoft.dto.user.userResponse.UserResponse;
import peaksoft.dto.user.userResponse.UserResponses;

import java.util.List;

public interface UserService {

    UserResponses authenticate(UserRequest userRequest);

    SimpleResponse register(UserRequest userRequest);

    List<UserResponse> getAllUsers();

    UserResponse getUserResponseById(Long id);

    SimpleResponse update(Long id, UserRequest userRequest);

    SimpleResponse deleteUser(Long Id);

    SimpleResponse answer(Long restaurantId, Long userId, String word);

}
