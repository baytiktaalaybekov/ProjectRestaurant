package peaksoft.dto.pagination;

import lombok.Builder;
import peaksoft.dto.category.categoryResponse.CategoryResponse;
import peaksoft.dto.user.userResponse.UserResponse;

import java.util.List;
@Builder
public record PaginationCategoryResponse(
        List<CategoryResponse> users,
        int size,
        int page
) {
}
