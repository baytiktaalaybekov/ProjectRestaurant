package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.AssignRequest;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.cheque.chequeRequest.ChequeRequest;
import peaksoft.dto.cheque.chequeResponse.ChequeResponse;
import peaksoft.service.ChequesService;

import java.util.List;

@RestController
@RequestMapping("/cheques")
@RequiredArgsConstructor
public class ChequeApi {

    private final ChequesService chequesService;

    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER')")
    @PostMapping
    public SimpleResponse save(@RequestBody ChequeRequest chequeRequest) {
        return chequesService.saveCheque(chequeRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<ChequeResponse> getAllCheque() {
        return chequesService.getAllCheques();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{cheId}")
    public ChequeResponse getChequeById(@PathVariable Long cheId) {
        return chequesService.getByIdCheque(cheId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{cheId}")
    public SimpleResponse delete(@PathVariable Long cheId) {
        return chequesService.deleteByIdCheque(cheId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{chequeId}")
    public SimpleResponse update(@PathVariable Long chequeId, @RequestBody ChequeRequest chequeRequest) {
        return chequesService.updateCheque(chequeId, chequeRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/assign")
    public SimpleResponse assign(@RequestBody AssignRequest assignRequest) {
        return chequesService.assignChequeToMenuItem(assignRequest);
    }

}
