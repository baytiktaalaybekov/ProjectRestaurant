package peaksoft.service.Impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.jwt.JwtUtil;
import peaksoft.dto.registr.AuthenticationResponse;
import peaksoft.dto.registr.signInRequest.SignInRequest;
import peaksoft.dto.registr.signUpRequest.SignUpRequest;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.repository.UserRepository;
import peaksoft.service.AuthenticationService;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthenticationSerImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())){
            throw new EntityExistsException(String.format(
                    "User with email: %s already exists!",signUpRequest.getEmail()));
        }


        User user= User.builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(Role.WAITER)
                .dateOfBirth(LocalDate.now())
                .build();
        userRepository.save(user);

        String jwToken= jwtUtil.generatorToken(user);
        return AuthenticationResponse.builder()
                .token(jwToken)
                .email(user.getEmail())
                .build();

    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(
                        "User with email: " + signInRequest.getEmail() + " not found"
                ));
        if (signInRequest.getEmail().isBlank()){
            throw new BadCredentialsException("Email doesn't exist");
        }
        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())){
            throw new BadCredentialsException("Incorrect password!");
        }
        String jwToken= jwtUtil.generatorToken(user);

        return AuthenticationResponse
                .builder()
                .email(user.getEmail())
                .token(jwToken)
                .build();
    }


    }

