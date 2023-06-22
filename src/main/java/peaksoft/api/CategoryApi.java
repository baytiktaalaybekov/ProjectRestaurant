package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.category.categoryRequest.CategoryRequest;
import peaksoft.dto.category.categoryResponse.CategoryResponse;
import peaksoft.service.CategoryService;

import java.util.List;

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
    public List<CategoryResponse> getAll() {
        return categoryService.getAllCategory();
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
