package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.category.categoryRequest.CategoryRequest;
import peaksoft.dto.category.categoryResponse.CategoryResponse;
import peaksoft.entity.Category;

import java.util.List;

public interface CategoryService {

    SimpleResponse saveCategory(CategoryRequest categoryRequest);

    List<CategoryResponse> getAllCategory();

    CategoryResponse getByIdCategoryId(Long id);

    SimpleResponse deleteCategoriesBiId(Long id);

    SimpleResponse updateCategory(Long id, CategoryRequest categoryRequest);

}
