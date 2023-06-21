package peaksoft.dto.pagination;

import lombok.Builder;
import peaksoft.dto.subcategory.subcategoryResponse.SubcategoryResponse;
import peaksoft.dto.user.userResponse.UserResponse;

import java.util.List;
@Builder
public record PaginationSubCategoryResponse(
        List<SubcategoryResponse> users,
        int size,
        int page
) {
}
