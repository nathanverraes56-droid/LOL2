package com.basespringboot.controller;

import com.basespringboot.security.JwtUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        // Vérifie que l'utilisateur existe avec le bon mot de passe
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        // Génère et retourne le Bearer Token
        return jwtUtil.generateToken(authRequest.getUsername());
    }
}

@Getter
@Setter
class AuthRequest {
    private String username;
    private String password;
}
