package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.dto.subcategory.subcategoryResponse.SubcategoryResponse;
import peaksoft.entity.SubCategory;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    @Query("select new peaksoft.dto.subcategory.subcategoryResponse.SubcategoryResponse(s.id, s.name,s.categories.name,s.categories.id) from SubCategory s")
    Page<SubcategoryResponse> getAllSubCategory(Pageable pageable);


    @Query("select new peaksoft.dto.subcategory.subcategoryResponse.SubcategoryResponse(s.id, s.name,s.categories.name,s.categories.id) from SubCategory s where s.id= :subId")
    Optional<SubcategoryResponse> getByIdSub(Long subId);

    @Query("select new peaksoft.dto.subcategory.subcategoryResponse.SubcategoryResponse(s.id, s.name, s.categories.name, s.categories.id) " +
            "from SubCategory s " +
            "where s.categories.id = :categoryId " +
            "order by case when :ascOrDesc = 'asc' then s.name end asc, " +
            "case when :ascOrDesc = 'desc' then s.name end desc")
    List<SubcategoryResponse> getAllSubCategoryOrderByCategoryName(@Param("categoryId") Long categoryId, @Param("ascOrDesc") String ascOrDesc);




}