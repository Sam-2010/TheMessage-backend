package com.Sameer.TheMessage.Service;

import com.Sameer.TheMessage.Dto.LoginRequestDto;
import com.Sameer.TheMessage.Dto.AuthResponseDto;
import com.Sameer.TheMessage.Dto.RegisterRequestDto;

public interface LoginService {
    AuthResponseDto authenticate(LoginRequestDto loginRequestDto);
    AuthResponseDto register(RegisterRequestDto registerRequestDto);
}
