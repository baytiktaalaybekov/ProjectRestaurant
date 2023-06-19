package peaksoft.dto.cheque.chequeRequest;

import lombok.Getter;
import lombok.Setter;
import peaksoft.entity.MenuItem;

import java.time.ZonedDateTime;
import java.util.List;

@Getter @Setter
public class ChequeRequest {

    private Long waiterId;

    private List<MenuItem> items;

    public ChequeRequest(Long waiterId, List<MenuItem> items) {
        this.waiterId = waiterId;
        this.items = items;
    }

    public ChequeRequest() {
    }
}
