package com.Sameer.TheMessage.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class LoginRequestDto {
    String userName;
    String passWord;
}
