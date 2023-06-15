package peaksoft.dto.user.userRequest;

import lombok.Builder;
import peaksoft.enums.Role;

@Builder
public record UserRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        int experience,
        String phoneNumber,
        Role role,
        Long restaurantId
) {
}
