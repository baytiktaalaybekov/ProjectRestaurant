package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.dto.registr.AuthenticationResponse;
import peaksoft.dto.registr.signInRequest.SignInRequest;
import peaksoft.dto.registr.signUpRequest.SignUpRequest;
import peaksoft.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationApi {

    private final AuthenticationService authenticationSer;

    @PostMapping("/signUp")
    AuthenticationResponse signUp(@RequestBody SignUpRequest signUpRequest){
        return authenticationSer.signUp(signUpRequest);
    }

    @PostMapping("/signIn")
    AuthenticationResponse signIn(@RequestBody SignInRequest signInRequest){
        return authenticationSer.signIn(signInRequest);
    }

}
