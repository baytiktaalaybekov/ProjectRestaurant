package peaksoft.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.pagination.PaginationSubCategoryResponse;
import peaksoft.dto.pagination.PaginationUserResponse;
import peaksoft.dto.subcategory.subcategoryRequest.SubcategoryRequest;
import peaksoft.dto.subcategory.subcategoryResponse.SubcategoryResponse;
import peaksoft.dto.user.userResponse.UserResponse;
import peaksoft.entity.Category;
import peaksoft.entity.SubCategory;
import peaksoft.exception.BadRequestException;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.CategoryRepository;
import peaksoft.repository.SubCategoryRepository;
import peaksoft.service.SubcategoryService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubcategorySeImpl implements SubcategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public SimpleResponse save(Long categoryId, SubcategoryRequest subCategoryRequest) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new BadRequestException(
                String.format("Category with id: %d doesn't exist", categoryId)));

        SubCategory subCategory = new SubCategory();
        subCategory.setName(subCategoryRequest.name());

        category.addSubCategory(subCategory);
        subCategory.setCategories(category);

        subCategoryRepository.save(subCategory);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Sub Category with name: %s successfully SAVED!", subCategoryRequest.name())).build();
    }

    @Override
    public PaginationSubCategoryResponse getAllBySubCategory(int pageSize,int currentPage) {
        Pageable pageable= PageRequest.of(currentPage-1,pageSize);
        Page<SubcategoryResponse> allUsers=subCategoryRepository.getAllSubCategory(pageable);
        return PaginationSubCategoryResponse
                .builder()
                .users(allUsers.getContent())
                .page(allUsers.getNumber()+1)
                .size(allUsers.getTotalPages())
                .build();
    }

    @Override
    public SubcategoryResponse getSubCategoryById(Long subId) {
        return subCategoryRepository.getByIdSub(subId).orElseThrow(() ->
                new NotFoundException("Subcategory with id: " + subId + "Not found"));
    }

    @Override
    public SimpleResponse update(Long subId, SubcategoryRequest subcategoryRequest) {
        SubCategory subCategory = subCategoryRepository.findById(subId).orElseThrow(() -> new NotFoundException(
                String.format("Sub category with Id: %d doesn't exist", subId)
        ));
        log.info("update");
        if (!subCategoryRepository.existsById(subId)) {
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Sub Category with id:" + subId + " doesn't exist").build();
        }

        subCategory.setName(subcategoryRequest.name());
        subCategoryRepository.save(subCategory);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Sub Category with id: %d successfully UPDATED", subId)).build();
    }

    @Override
    public SimpleResponse delete(Long subCategoryId) {
        if (!subCategoryRepository.existsById(subCategoryId)) {
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message(String.format("Sub category with id: %d doesn't exist", subCategoryId)).build();
        }
        subCategoryRepository.deleteById(subCategoryId);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Sub category with id: %d successfully DELETED", subCategoryId)).build();
    }

    @Override
    public Map<String, List<SubCategory>> findAllGroupByCategory() {
        return subCategoryRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(subcategory -> subcategory.getCategories().getName()));
    }

    @Override
    public List<SubcategoryResponse> getAllSubCategoryOrderByCategoryName(Long categoryId, String ascOrDesc) {
        return subCategoryRepository.getAllSubCategoryOrderByCategoryName(categoryId, ascOrDesc);
    }


}
