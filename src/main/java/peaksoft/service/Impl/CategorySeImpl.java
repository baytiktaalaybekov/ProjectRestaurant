package peaksoft.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.category.categoryRequest.CategoryRequest;
import peaksoft.dto.category.categoryResponse.CategoryResponse;
import peaksoft.dto.pagination.PaginationCategoryResponse;
import peaksoft.dto.pagination.PaginationUserResponse;
import peaksoft.dto.user.userResponse.UserResponse;
import peaksoft.entity.Category;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.CategoryRepository;
import peaksoft.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategorySeImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public SimpleResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        categoryRepository.save(category);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Successfully saved..")
                .build();
    }

    @Override
    public PaginationCategoryResponse getAllCategory(int pageSize,int currentPage) {
        Pageable pageable= PageRequest.of(currentPage-1,pageSize);
        Page<CategoryResponse> allUsers=categoryRepository.getAllCategory(pageable);
        return PaginationCategoryResponse
                .builder()
                .users(allUsers.getContent())
                .page(allUsers.getNumber()+1)
                .size(allUsers.getTotalPages())
                .build();

    }

    @Override
    public CategoryResponse getByIdCategoryId(Long id) {
        return categoryRepository.getByIdCategoryId(id).orElseThrow(
                ()->new NotFoundException("Category with id: " +id+ "Not found")
        );
    }

    @Override
    public SimpleResponse deleteCategoriesBiId(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Not Found!"));
        categoryRepository.delete(category);
        return SimpleResponse
                .builder()
                .status(HttpStatus.OK)
                .message("Successfully delete.")
                .build();
    }

    @Override
    public SimpleResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Not Found!"));
        category.setName(categoryRequest.getName());
        log.info("update");
        categoryRepository.save(category);
        return SimpleResponse.builder().status(HttpStatus.OK).message("Update").build();
    }




}
