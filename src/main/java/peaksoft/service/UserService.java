package peaksoft.service;

import org.springframework.data.domain.Pageable;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.pagination.PaginationUserResponse;
import peaksoft.dto.user.userRequest.UserRequest;
import peaksoft.dto.user.userResponse.UserResponse;
import peaksoft.dto.user.userResponse.UserResponses;

import java.util.List;

public interface UserService {

    UserResponses authenticate(UserRequest userRequest);

    SimpleResponse register(UserRequest userRequest);

    PaginationUserResponse getAllUsers(int pageSize,int currentPage);

    UserResponse getUserResponseById(Long id);

    SimpleResponse update(Long id, UserRequest userRequest);

    SimpleResponse deleteUser(Long Id);

    SimpleResponse answer(Long restaurantId, Long userId, String word);

//    PaginationResponse getPagination(int size, int page);
}
