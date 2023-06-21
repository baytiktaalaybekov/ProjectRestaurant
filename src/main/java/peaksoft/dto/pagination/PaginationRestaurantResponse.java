package peaksoft.dto.pagination;

import lombok.Builder;
import peaksoft.dto.restaurant.restaurantResponse.RestaurantResponse;
import peaksoft.dto.user.userResponse.UserResponse;

import java.util.List;
@Builder
public record PaginationRestaurantResponse(
        List<RestaurantResponse> users,
        int size,
        int page
) {
}
