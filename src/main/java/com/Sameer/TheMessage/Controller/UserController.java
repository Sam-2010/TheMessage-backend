package com.Sameer.TheMessage.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity getCurrentUser(Authentication authentication) {
        // authentication.getPrincipal() returns whatever you passed as the 1st argument
        // into UsernamePasswordAuthenticationToken inside JwtAuthFilter (your userId)
        Object userId = authentication.getPrincipal();

        return ResponseEntity.ok(Map.of(
                "userId", userId,
                "authenticated", authentication.isAuthenticated()
        ));
    }
}