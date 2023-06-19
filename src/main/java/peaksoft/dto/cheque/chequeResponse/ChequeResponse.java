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

    private BigDecimal priceAverage;

    private int service;

    private BigDecimal grandTotal;

    public ChequeResponse(Long id, String fullName, List<MenuItem> items, BigDecimal priceAverage, int service, BigDecimal grandTotal) {
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
