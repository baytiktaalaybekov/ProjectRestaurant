package peaksoft.dto.cheque.chequeResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.entity.MenuItem;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
@Builder
public class ChequeResponse {

    private Long id;

    private String fullName;

    private List<MenuItem> items;

    private int priceAverage;

    private int service;

    private int grandTotal;

    public ChequeResponse(Long id, String fullName, List<MenuItem> items, int priceAverage, int service, int grandTotal) {
        this.id = id;
        this.fullName = fullName;
        this.items = items;
        this.priceAverage = priceAverage;
        this.service = service;
        this.grandTotal = grandTotal;
    }

    public ChequeResponse() {
    }
}
