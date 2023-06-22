package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.menuItem.menuItemRequest.MenuItemRequest;
import peaksoft.dto.menuItem.menuItemResponse.MenuItemResponse;
import peaksoft.dto.pagination.PaginationMenuItemResponse;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

public interface MenuItemService {

    SimpleResponse saveManu( MenuItemRequest menuRequest);

    List<MenuItemResponse> getAllMenus();

    MenuItemResponse getByMenuId(Long menuId);

    SimpleResponse updateMenu(Long menuId, MenuItemRequest request);

    SimpleResponse deleteManu(Long Id);

    List<MenuItemResponse> sort(String ascOrDesc);

    List<MenuItemResponse> filter(Boolean isVegetarian);

    List<MenuItemResponse> searchAllByKeyWord(String keyWord);


}
