package peaksoft.dto;

import lombok.*;
import org.springframework.http.HttpStatus;
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpleResponse {

    private HttpStatus status;

    private String message;


}
