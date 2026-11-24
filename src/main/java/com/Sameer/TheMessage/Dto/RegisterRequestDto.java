package com.Sameer.TheMessage.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequestDto {
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 30, message = "Username must contain 3 to 30 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$",message = "Username can on only contain letters, numbers and underscore")
    String userName;
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "password must be atleast 8 characters")
    String passWord;
}
