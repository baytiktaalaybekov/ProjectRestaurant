package peaksoft.dto.registr.signUpRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int experience;
    private String phoneNumber;

    public SignUpRequest(String firstName, String lastName, String email, String password, int experience, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.experience = experience;
        this.phoneNumber = phoneNumber;
    }

    public SignUpRequest() {
    }
}
