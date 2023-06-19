package peaksoft.dto.subcategory.subcategoryResponse;

import lombok.Builder;

@Builder
public record SubcategoryResponse(
        Long id,
        String name,
        String categoryName,
        Long categoryId


) {
}
