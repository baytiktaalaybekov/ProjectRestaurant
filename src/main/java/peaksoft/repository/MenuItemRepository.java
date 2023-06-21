package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.dto.menuItem.menuItemResponse.MenuItemResponse;
import peaksoft.entity.MenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    @Query("select new peaksoft.dto.menuItem.menuItemResponse.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian) from MenuItem m")
    Page<MenuItemResponse> getAllMenus(Pageable pageable);

    @Query("select new peaksoft.dto.menuItem.menuItemResponse.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian) from MenuItem m where m.id= :menuId")
    Optional<MenuItemResponse> getByMenuId(Long menuId);


    @Query("SELECT NEW peaksoft.dto.menuItem.menuItemResponse.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian) " +
            "FROM MenuItem m " +
            "ORDER BY " +
            "CASE WHEN :ascOrDesc = 'asc' THEN m.price END ASC, " +
            "CASE WHEN :ascOrDesc = 'desc' THEN m.price END DESC")
    List<MenuItemResponse> sort(@Param("ascOrDesc") String ascOrDesc);

    @Query("SELECT NEW peaksoft.dto.menuItem.menuItemResponse.MenuItemResponse(m.id, m.name, m.image, m.price, m.description, m.isVegetarian) " +
            "FROM MenuItem m " +
            "WHERE m.isVegetarian = :isVegetarian")
    List<MenuItemResponse> filter(@Param("isVegetarian") Boolean isVegetarian);


    @Query("select new peaksoft.dto.menuItem.menuItemResponse.MenuItemResponse" +
            "(m.id, m.name, m.image, m.price, m.description, m.isVegetarian) " +
            "from MenuItem m where m.name ilike concat('%', :keyWord, '%') or m.description " +
            "ilike concat('%', :keyWord, '%') or m.subcategory.name ilike concat('%', :keyWord, '%')" +
            " or m.subcategory.categories.name ilike concat('%', :keyWord, '%')")
    List<MenuItemResponse> searchAllByKeyWord(String keyWord);

    Optional<MenuItem> findMenuItemByName(String menuItemName);

}