package by.misevich.app.core.services;

import by.misevich.app.core.convertors.dto.AuthRequestDTO;
import by.misevich.app.core.convertors.dto.AuthResponseDTO;
import by.misevich.app.core.security.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils tokenUtils;
    private final UserDetailsService userDetailsService;

    public ResponseEntity<AuthResponseDTO> login(AuthRequestDTO request) {
        final String username = request.getUsername();
        final String password = request.getSecurePassword();
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = tokenUtils.generateToken(userDetails);

        AuthResponseDTO response = new AuthResponseDTO();
        response.setUsername(username);
        response.setToken(token);

        return ResponseEntity.ok(response);
    }
}
