package peaksoft.dto.restaurant.restaurantRequest;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter @Setter
public class CheckOneDayRestaurantRequest {
    private String restaurantName;
    private ZonedDateTime date;


}
