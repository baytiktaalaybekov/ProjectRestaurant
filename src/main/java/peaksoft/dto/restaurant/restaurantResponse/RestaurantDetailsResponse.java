package peaksoft.dto.restaurant.restaurantResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RestaurantDetailsResponse {

    private Long id;


    private Long user_count;

    public RestaurantDetailsResponse(Long id, Long user_count) {
        this.id = id;

        this.user_count = user_count;
    }

    public RestaurantDetailsResponse() {
    }
}

