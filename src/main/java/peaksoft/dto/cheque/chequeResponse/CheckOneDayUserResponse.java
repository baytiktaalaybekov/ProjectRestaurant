package peaksoft.dto.cheque.chequeResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CheckOneDayUserResponse {

    private String fullName;


    private int numberOfCheques;

    private int averageGrandTotal;


    public CheckOneDayUserResponse(String fullName,  int numberOfCheques, int averageGrandTotal) {
         this.fullName = fullName;
//        this.numberOfUsers = numberOfUsers;
        this.numberOfCheques = numberOfCheques;
        this.averageGrandTotal = averageGrandTotal;
    }

    public CheckOneDayUserResponse() {
    }
}
