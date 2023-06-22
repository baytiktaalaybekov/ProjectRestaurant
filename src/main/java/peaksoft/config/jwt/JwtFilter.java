package peaksoft.config.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import peaksoft.entity.User;
import peaksoft.repository.UserRepository;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String TokenHeader = request.getHeader("Authorization");
        if (TokenHeader != null && TokenHeader.startsWith("Bearer ")) {
            String token = TokenHeader.substring(7);
            try {
                if (StringUtils.hasText(token)) {
                    String username = jwtUtil.validateToken(token);
                    User user = userRepository.findByEmail(username).orElseThrow(() -> new EntityNotFoundException
                            ("user with email: " + username + " is not found"));

                    SecurityContextHolder.getContext()
                            .setAuthentication(
                                    new UsernamePasswordAuthenticationToken(
                                            user.getEmail(), null, user.getAuthorities()));
                }
            } catch (JWTVerificationException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "invalid token");
            }
        }
        filterChain.doFilter(request, response);
    }



}
