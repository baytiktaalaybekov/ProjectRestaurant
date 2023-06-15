package peaksoft.service;

import peaksoft.dto.registr.AuthenticationResponse;
import peaksoft.dto.registr.signInRequest.SignInRequest;
import peaksoft.dto.registr.signUpRequest.SignUpRequest;

public interface AuthenticationService {

    AuthenticationResponse signUp(SignUpRequest signUpRequest);

    AuthenticationResponse signIn(SignInRequest signInRequest);

}
