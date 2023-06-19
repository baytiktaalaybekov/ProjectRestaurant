package peaksoft.dto.cheque.chequeRequest;

import lombok.Data;

import java.util.List;

@Data
public class ChequeRequest {

    private Long userId;

//    private List<String> menuItemNames;
    private List<Long> menuItemNames;


}
