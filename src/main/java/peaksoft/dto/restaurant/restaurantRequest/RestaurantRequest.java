package peaksoft.dto.restaurant.restaurantRequest;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.RestType;
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRequest {

    private String name;

    private String location;

    private int numberOfUsers;

    @Enumerated(EnumType.STRING)
    private RestType restType;

    private int service;
}
