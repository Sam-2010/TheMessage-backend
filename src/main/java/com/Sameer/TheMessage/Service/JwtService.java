package com.Sameer.TheMessage.Service;

import com.Sameer.TheMessage.Entity.User;

public interface JwtService {
    String generateToken(User user);
    Long extractUserId(String token);
    String extractUserName(String token);

}
