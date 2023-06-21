package peaksoft.dto.pagination;

import lombok.Builder;
import peaksoft.dto.cheque.chequeResponse.ChequeResponse;
import peaksoft.dto.user.userResponse.UserResponse;

import java.util.List;
@Builder
public record PaginationChequeResponse(
        List<ChequeResponse> users,
        int size,
        int page
) {
}
