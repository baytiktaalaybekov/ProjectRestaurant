package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.category.categoryRequest.CategoryRequest;
import peaksoft.dto.category.categoryResponse.CategoryResponse;
import peaksoft.dto.pagination.PaginationCategoryResponse;
import peaksoft.service.CategoryService;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryApi {

    private final CategoryService categoryService;


    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse save(@RequestBody CategoryRequest request) {
        return categoryService.saveCategory(request);
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public PaginationCategoryResponse getAll(@RequestParam int pageSize, int currentPage) {
        return categoryService.getAllCategory(pageSize, currentPage);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse delete(@PathVariable Long id) {
        return categoryService.deleteCategoriesBiId(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CategoryResponse getById(@PathVariable Long id) {
        return categoryService.getByIdCategoryId(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse update(@PathVariable Long id, @RequestBody CategoryRequest request) {
        return categoryService.updateCategory(id, request);
    }
}
