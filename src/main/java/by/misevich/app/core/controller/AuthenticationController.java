package by.misevich.app.core.controller;

import by.misevich.app.core.convertors.dto.AuthRequestDTO;
import by.misevich.app.core.convertors.dto.AuthResponseDTO;
import by.misevich.app.core.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        return authenticationService.login(request);
    }
}
