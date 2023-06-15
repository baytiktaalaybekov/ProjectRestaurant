package peaksoft.dto.user.userResponse;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import peaksoft.enums.Role;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponses {

    private String token;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;


}
