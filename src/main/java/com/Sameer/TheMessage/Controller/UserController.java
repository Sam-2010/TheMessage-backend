package com.Sameer.TheMessage.Controller;

import com.Sameer.TheMessage.Entity.User;
import com.Sameer.TheMessage.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity getCurrentUser(Authentication authentication) {
        // authentication.getPrincipal() returns whatever you passed as the 1st argument
        // into UsernamePasswordAuthenticationToken inside JwtAuthFilter (your userId)
        Long userId = (Long)authentication.getPrincipal();

        User user=userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));

        return ResponseEntity.ok(Map.of(
                "userId", user.getId(),
                "userName", user.getUserName()
        ));
    }
}