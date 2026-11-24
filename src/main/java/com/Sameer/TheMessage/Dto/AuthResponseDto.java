package com.Sameer.TheMessage.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDto {
    Long userId;
    String userName;
    String token;
}
