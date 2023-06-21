package peaksoft.dto.cheque.chequeRequest;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class CheckOneDayUserRequest {
    private Long userId;
    private ZonedDateTime date;
}
