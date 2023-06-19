package peaksoft.dto.stopList.stopListResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.entity.MenuItem;

import java.time.ZonedDateTime;
@Getter
@Setter
@Builder
public class StopListResponse {
    private Long  id;
    private String reason;
    private ZonedDateTime date;
    private String menuitemName;

    public StopListResponse(Long id, String reason, ZonedDateTime date,String menuitemName) {
        this.id = id;
        this.reason = reason;
        this.date = date;
        this.menuitemName = menuitemName;
    }

    public StopListResponse() {
    }
}
