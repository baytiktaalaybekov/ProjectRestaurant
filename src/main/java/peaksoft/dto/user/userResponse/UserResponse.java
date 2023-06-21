package peaksoft.dto.user.userResponse;

import lombok.*;
import peaksoft.enums.Role;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Role role;




}
