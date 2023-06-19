package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.subcategory.subcategoryRequest.SubcategoryRequest;
import peaksoft.dto.subcategory.subcategoryResponse.SubcategoryResponse;
import peaksoft.entity.SubCategory;
import peaksoft.service.SubcategoryService;

import java.util.List;
import java.util.Map;
//todo Baytik
@RestController
@RequestMapping("/subcategories")
@RequiredArgsConstructor
public class SubcategoryApi {

    private final SubcategoryService subcategoryService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{categoryId}")
    public SimpleResponse save(@PathVariable Long categoryId, @RequestBody SubcategoryRequest subcategoryRequest){
        return subcategoryService.save(categoryId,subcategoryRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<SubcategoryResponse> getAllSubcategory (){
        return subcategoryService.getAllBySubCategory();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public SimpleResponse update (@PathVariable Long id,@RequestBody SubcategoryRequest subcategoryRequest){
        return subcategoryService.update(id,subcategoryRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public SubcategoryResponse getById(@PathVariable Long id){
        return subcategoryService.getSubCategoryById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id){
        return subcategoryService.delete(id);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/find")
    public Map<String,List<SubCategory>> findAllGroupByCategory(){
        return subcategoryService.findAllGroupByCategory();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/order/{categoryId}")
    public List<SubcategoryResponse> getAllSubCategoryOrderByCategoryName(@PathVariable Long categoryId,@RequestParam(required = false) String ascOrDesc){
        return subcategoryService.getAllSubCategoryOrderByCategoryName(categoryId,ascOrDesc);
    }


}
