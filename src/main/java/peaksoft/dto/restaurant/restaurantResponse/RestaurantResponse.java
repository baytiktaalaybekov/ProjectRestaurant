package peaksoft.dto.restaurant.restaurantResponse;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantResponse {

    private Long id;

    private String name;

    private String location;


}
