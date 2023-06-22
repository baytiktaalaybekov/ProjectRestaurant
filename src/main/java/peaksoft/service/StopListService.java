package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.pagination.PaginationStopListResponse;
import peaksoft.dto.stopList.stopListRequest.StopListRequest;
import peaksoft.dto.stopList.stopListResponse.StopListResponse;

import java.time.ZonedDateTime;
import java.util.List;

public interface StopListService {

    SimpleResponse saveStopList(StopListRequest stopListRequest);

    List<StopListResponse> getAllStopList();

    StopListResponse getStopListById(Long id);

    SimpleResponse updateStopList(Long id, StopListRequest stopListRequest);

    SimpleResponse deleteStopListById(Long id);
}
