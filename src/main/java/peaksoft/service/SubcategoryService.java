package peaksoft.service;

import org.springframework.data.repository.query.Param;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.pagination.PaginationSubCategoryResponse;
import peaksoft.dto.subcategory.subcategoryRequest.SubcategoryRequest;
import peaksoft.dto.subcategory.subcategoryResponse.SubcategoryResponse;
import peaksoft.entity.SubCategory;

import java.util.List;
import java.util.Map;

public interface SubcategoryService {

    SimpleResponse save(Long categoryId, SubcategoryRequest subCategoryRequest);

   PaginationSubCategoryResponse getAllBySubCategory(int pageSize,int currentPage);

    SubcategoryResponse getSubCategoryById(Long subId);

    SimpleResponse update(Long subId, SubcategoryRequest subcategoryRequest);

    SimpleResponse delete(Long subCategoryId);

    Map<String,List<SubCategory>> findAllGroupByCategory();

    List<SubcategoryResponse> getAllSubCategoryOrderByCategoryName(Long categoryId,String ascOrDesc);


//    SubCategoryPaginationResponse getSubCategoryResponse(int page, int size);
}


