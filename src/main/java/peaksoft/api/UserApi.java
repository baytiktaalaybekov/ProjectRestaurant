package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;

import peaksoft.dto.user.userRequest.UserRequest;
import peaksoft.dto.user.userResponse.UserResponse;
import peaksoft.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;


    @PostMapping
    public SimpleResponse register(@RequestBody UserRequest userRequest) {
        return userService.register(userRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER,CHEF')")
    @GetMapping("/{userId}")
    public UserResponse getById(@PathVariable Long userId) {
        return userService.getUserResponseById(userId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<UserResponse> getAllUSer() {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER,CHEF')")
    @PutMapping("/{id}")
    public SimpleResponse updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userService.update(id, userRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER,CHEF')")
    @DeleteMapping("/{id}")
    public SimpleResponse deleteById(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/answer/{restaurantId}/{userId}")
    public SimpleResponse answer(@PathVariable Long restaurantId, @PathVariable Long userId, @RequestParam String word) {
        return userService.answer(restaurantId, userId, word);
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/pagination")
//    public PaginationResponse getPagination(@RequestParam int page, @RequestParam int size) {
//        return userService.getPagination(page, size);
//    }


}
