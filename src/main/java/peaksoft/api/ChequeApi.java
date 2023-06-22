package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.cheque.chequeRequest.CheckOneDayUserRequest;
import peaksoft.dto.cheque.chequeRequest.ChequeRequest;
import peaksoft.dto.cheque.chequeResponse.CheckOneDayUserResponse;
import peaksoft.dto.restaurant.restaurantRequest.CheckOneDayRestaurantRequest;
import peaksoft.dto.restaurant.restaurantResponse.CheckOneDayRestaurantResponse;
import peaksoft.service.ChequesService;

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
    @GetMapping("/userTotal")
    public CheckOneDayUserResponse findAllChequesOneDayTotalAmount(@RequestBody CheckOneDayUserRequest checkOneDayUserRequest) {
        return chequesService.totalSumByUser(checkOneDayUserRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/restTotal")
    public CheckOneDayRestaurantResponse checkOneDayRestaurantResponse(@RequestBody CheckOneDayRestaurantRequest checkOneDayRestaurantRequest) {
        return chequesService.totalAvgByRestaurant(checkOneDayRestaurantRequest);
    }


}
