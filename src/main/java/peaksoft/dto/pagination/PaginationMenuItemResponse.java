package peaksoft.dto.pagination;

import lombok.Builder;
import peaksoft.dto.menuItem.menuItemResponse.MenuItemResponse;
import peaksoft.dto.user.userResponse.UserResponse;

import java.util.List;
@Builder
public record PaginationMenuItemResponse(
        List<MenuItemResponse> users,
        int size,
        int page
) {
}
