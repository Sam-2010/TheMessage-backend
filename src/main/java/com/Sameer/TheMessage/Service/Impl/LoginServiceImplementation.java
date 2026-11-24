package com.Sameer.TheMessage.Service.Impl;

import com.Sameer.TheMessage.Dto.LoginRequestDto;
import com.Sameer.TheMessage.Dto.AuthResponseDto;
import com.Sameer.TheMessage.Dto.RegisterRequestDto;
import com.Sameer.TheMessage.Entity.User;
import com.Sameer.TheMessage.Exceptions.UsernameAlreadyExistsException;
import com.Sameer.TheMessage.Repository.UserRepository;
import com.Sameer.TheMessage.Service.JwtService;
import com.Sameer.TheMessage.Service.LoginService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImplementation implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    public AuthResponseDto authenticate(LoginRequestDto loginRequestDto) {
        User user= userRepository.findByUserName(loginRequestDto.getUserName());
        if(user==null)throw new BadCredentialsException("Invalid Username or Password");
        if(!passwordEncoder.matches(loginRequestDto.getPassWord(), user.getPassWord())){
            throw new BadCredentialsException("Invalid Username or Password");
        }
        String token=jwtService.generateToken(user);
        return new AuthResponseDto(user.getId(),user.getUserName(),token);
    }

    @Override
    @Transactional
    //Transactional is used so if any error occurs after saving the user it can be reverted
    public AuthResponseDto register(RegisterRequestDto registerRequestDto) {
        if(userRepository.existsByUserName(registerRequestDto.getUserName())){
            throw new UsernameAlreadyExistsException("Username Already taken");
        }
        User user=new User();
        user.setUserName(registerRequestDto.getUserName());
        user.setPassWord(passwordEncoder.encode(registerRequestDto.getPassWord()));
        User saved=userRepository.save(user);

        String token = jwtService.generateToken(saved);
        return new AuthResponseDto(saved.getId(), saved.getUserName(), token);
    }


}
