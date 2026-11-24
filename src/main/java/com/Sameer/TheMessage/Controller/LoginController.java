package com.Sameer.TheMessage.Controller;

import com.Sameer.TheMessage.Dto.LoginRequestDto;
import com.Sameer.TheMessage.Dto.AuthResponseDto;
import com.Sameer.TheMessage.Dto.RegisterRequestDto;
import com.Sameer.TheMessage.Service.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    private final LoginService loginService;
    public LoginController(LoginService loginService){this.loginService=loginService;}

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(loginService.authenticate(loginRequestDto));
        //here only the situation when the login is sucessful condition is handled.
        // if it is unsucessful then it is sent to global exception handler.
        //handled by handleBadCredential
    }
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody RegisterRequestDto registerRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(loginService.register(registerRequestDto));
        //here only the situation when the registration is successful condition is handled
        //if unsuccessful it is sent to globalExceptionHandler
        //handled by usernameAlreadyExists
    }
}
