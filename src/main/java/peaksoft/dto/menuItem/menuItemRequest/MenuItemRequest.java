package peaksoft.dto.menuItem.menuItemRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemRequest {

    private String name;
    private String image;
    private int price;
    private String description;
    private Boolean isVegetarian;
    private Long restaurantId;
    private Long subcategoryId;

    public MenuItemRequest(String name, String image, int price, String description, Boolean isVegetarian, Long restaurantId, Long subcategoryId) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.isVegetarian = isVegetarian;
        this.restaurantId = restaurantId;
        this.subcategoryId = subcategoryId;
    }

    public MenuItemRequest() {
    }
}
