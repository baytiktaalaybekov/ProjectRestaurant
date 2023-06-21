package peaksoft.dto.pagination;

import lombok.Builder;
import peaksoft.dto.stopList.stopListResponse.StopListResponse;
import peaksoft.dto.user.userResponse.UserResponse;

import java.util.List;
@Builder
public record PaginationStopListResponse(
        List<StopListResponse> users,
        int size,
        int page
) {
}
