package peaksoft.dto.cheque.chequeResponse;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter @Setter
public class ChequeResponse {

    private Long id;

    private ZonedDateTime date;

    private String fullName;

    private List<MenuItemChequeResponse> items;

    private int priceAverage;

    private int service;

    private int grandTotal;

    public ChequeResponse(Long id, ZonedDateTime date, String fullName, int priceAverage, int service) {
        this.id = id;
        this.date = date;
        this.fullName = fullName;
        this.priceAverage = priceAverage;
        this.service = service;
    }

    public ChequeResponse() {
    }
}
