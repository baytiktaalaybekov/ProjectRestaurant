package peaksoft.dto.stopList.stopListRequest;

import lombok.Getter;
import lombok.Setter;
import peaksoft.entity.MenuItem;

import java.time.ZonedDateTime;
@Setter
@Getter

public class StopListRequest {
    private String reason;
        private ZonedDateTime date;
    private Long menuItemId;

    public StopListRequest(String reason, ZonedDateTime date, Long menuItemId) {
        this.reason = reason;
        this.date = date;
        this.menuItemId = menuItemId;
    }

    public StopListRequest() {
    }
}
