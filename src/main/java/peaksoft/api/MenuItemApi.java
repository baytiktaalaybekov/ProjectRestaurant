package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.menuItem.menuItemRequest.MenuItemRequest;
import peaksoft.dto.menuItem.menuItemResponse.MenuItemResponse;
import peaksoft.dto.pagination.PaginationMenuItemResponse;
import peaksoft.service.MenuItemService;

import java.util.List;

@RestController
@RequestMapping("/menuItems")
@RequiredArgsConstructor
public class MenuItemApi {

    private final MenuItemService menuItemService;


    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @PostMapping
    public SimpleResponse save(@RequestBody MenuItemRequest menuItemRequest) {
        return menuItemService.saveManu(menuItemRequest);
    }

    @GetMapping
    public PaginationMenuItemResponse getAllMenuAndItem(@RequestParam int pageSize, int currentPage) {
        return menuItemService.getAllMenus(pageSize, currentPage);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @GetMapping("/{menuId}")
    public MenuItemResponse getByManuId(@PathVariable Long menuId) {
        return menuItemService.getByMenuId(menuId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @PutMapping("/{menuId}")
    public SimpleResponse updateMenu(@PathVariable Long menuId,
                                     @RequestBody MenuItemRequest request) {
        return menuItemService.updateMenu(menuId, request);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @DeleteMapping("/{menuId}")
    public SimpleResponse deleteMenu(@PathVariable Long menuId) {
        return menuItemService.deleteManu(menuId);

    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @GetMapping("/sort")
    public List<MenuItemResponse> sort(@RequestParam(required = false) String ascOrDesc) {
        return menuItemService.sort(ascOrDesc);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @GetMapping("/filter")
    List<MenuItemResponse> filter(@RequestParam(required = false) Boolean isVegetarian) {
        return menuItemService.filter(isVegetarian);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @GetMapping("/search")
    public List<MenuItemResponse> search(@RequestParam(required = false) String keyWord) {
        return menuItemService.searchAllByKeyWord(keyWord);
    }

}
