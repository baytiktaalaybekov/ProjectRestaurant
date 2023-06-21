package peaksoft.dto.pagination;

import lombok.Builder;
import peaksoft.dto.user.userResponse.UserResponse;

import java.util.List;


@Builder
public record PaginationUserResponse(
        List<UserResponse> users,
        int size,
        int page
) {

}
