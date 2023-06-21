package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.pagination.PaginationStopListResponse;
import peaksoft.dto.stopList.stopListRequest.StopListRequest;
import peaksoft.dto.stopList.stopListResponse.StopListResponse;
import peaksoft.service.StopListService;

@RestController
@RequestMapping("/stopLists")
@RequiredArgsConstructor
public class StopListApi {

    private final StopListService stopListService;


    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @PostMapping
    public SimpleResponse save(@RequestBody StopListRequest stopListRequest) {
        return stopListService.saveStopList(stopListRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @GetMapping
    public PaginationStopListResponse getAll(@RequestParam int pageSize, int currentPage) {
        return stopListService.getAllStopList(pageSize, currentPage);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @GetMapping("/{stopListId}")
    public StopListResponse getStopListId(@PathVariable Long stopListId) {
        return stopListService.getStopListById(stopListId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @PutMapping("/{stopListId}")
    public SimpleResponse update(@PathVariable Long stopListId, @RequestBody StopListRequest stopListRequest) {
        return stopListService.updateStopList(stopListId, stopListRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @DeleteMapping("/{stopListId}")
    public SimpleResponse delete(@PathVariable Long stopListId) {
        return stopListService.deleteStopListById(stopListId);
    }

}
