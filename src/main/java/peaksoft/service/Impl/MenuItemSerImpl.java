package peaksoft.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.menuItem.menuItemRequest.MenuItemRequest;
import peaksoft.dto.menuItem.menuItemResponse.MenuItemResponse;
import peaksoft.dto.pagination.PaginationMenuItemResponse;
import peaksoft.dto.pagination.PaginationUserResponse;
import peaksoft.dto.user.userResponse.UserResponse;
import peaksoft.entity.MenuItem;
import peaksoft.entity.Restaurant;
import peaksoft.entity.SubCategory;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.RestaurantRepository;
import peaksoft.repository.SubCategoryRepository;
import peaksoft.service.MenuItemService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuItemSerImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final SubCategoryRepository subCategoryRepository;

    @Override
    public SimpleResponse saveManu(MenuItemRequest menuRequest) {
        Restaurant restaurant = restaurantRepository.findById(menuRequest.getRestaurantId()).orElseThrow(
                () -> new NotFoundException("Restaurant with id: " + menuRequest.getRestaurantId() + "not found"));
        SubCategory subCategory = subCategoryRepository.findById(menuRequest.getSubcategoryId()).orElseThrow(
                () -> new NotFoundException("Subcategory with id: " + menuRequest.getSubcategoryId() + "not found"));
        if (menuRequest.getPrice() < 0) {
            return SimpleResponse.builder().status(HttpStatus.BAD_REQUEST)
                    .message("Price must be greater than 0").build();
        }
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuRequest.getName());
        menuItem.setImage(menuRequest.getImage());
        menuItem.setPrice(menuRequest.getPrice());
        menuItem.setDescription(menuRequest.getDescription());
        menuItem.setIsVegetarian(menuRequest.getIsVegetarian());
        restaurant.addMenuItem(menuItem);
        subCategory.getMenuItems().add(menuItem);
        menuItem.setRestaurant(restaurant);
        menuItem.setSubcategory(subCategory);
        menuItemRepository.save(menuItem);
        return SimpleResponse
                .builder()
                .status(HttpStatus.OK)
                .message("Save MenuItem..")
                .build();

    }

    @Override
    public PaginationMenuItemResponse getAllMenus(int pageSize,int currentPage) {
        Pageable pageable= PageRequest.of(currentPage-1,pageSize);
        Page<MenuItemResponse> allUsers=menuItemRepository.getAllMenus(pageable);
        return PaginationMenuItemResponse
                .builder()
                .users(allUsers.getContent())
                .page(allUsers.getNumber()+1)
                .size(allUsers.getTotalPages())
                .build();    }

    @Override
    public MenuItemResponse getByMenuId(Long menuId) {
        return menuItemRepository.getByMenuId(menuId).orElseThrow(
                () -> new NotFoundException("MenuItem with id: " + menuId + " Not found"));
    }

    @Override
    public SimpleResponse updateMenu(Long menuId, MenuItemRequest request) {
        if (!menuItemRepository.existsById(menuId)) {
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Menu with id:" + menuId + " doesn't exist").build();
        }
        MenuItem menuItem = menuItemRepository.findById(menuId).orElseThrow();
        menuItem.setName(request.getName());
        menuItem.setImage(request.getImage());
        if (request.getPrice() < 0) {
            return SimpleResponse.builder().status(HttpStatus.BAD_REQUEST).message("Price must be greater than 0").build();
        }
        menuItem.setPrice(request.getPrice());
        menuItem.setDescription(request.getDescription());
        menuItem.setIsVegetarian(request.getIsVegetarian());
        log.info("update");
        menuItemRepository.save(menuItem);
        return SimpleResponse.builder().status(HttpStatus.OK).message(
                String.format("Menu with name: %s successfully UPDATED", request.getName())).build();
    }

    @Override
    public SimpleResponse deleteManu(Long Id) {
        menuItemRepository.deleteById(Id);
        return SimpleResponse.builder().status(HttpStatus.OK)
                .message("Successfully deleted.!!").build();
    }

    @Override
    public List<MenuItemResponse> sort(String ascOrDesc) {
        return menuItemRepository.sort(ascOrDesc);
    }

    @Override
    public List<MenuItemResponse> filter(Boolean isVegetarian) {
        return menuItemRepository.filter(isVegetarian);
    }

    @Override
    public List<MenuItemResponse> searchAllByKeyWord(String keyWord) {
        return menuItemRepository.searchAllByKeyWord(keyWord);
    }
}
