package peaksoft.dto.restaurant.restaurantResponse;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter @Setter
public class CheckOneDayRestaurantResponse {
    private ZonedDateTime date ;
    private int numberOfCheques;
    private int averageGrandTotal;


}
