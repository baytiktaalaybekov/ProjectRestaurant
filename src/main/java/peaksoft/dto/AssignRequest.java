package peaksoft.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class AssignRequest {

    private Long chequeId;

    private Long menuItemId;


    public AssignRequest(Long chequeId, Long menuItemId) {
        this.chequeId = chequeId;
        this.menuItemId = menuItemId;
    }

    public AssignRequest() {
    }
}
