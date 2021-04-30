package thenhat.code.managerwebapp.service.security;

import thenhat.code.managerwebapp.model.entity.SecureToken;

public interface SecureTokenService {

    SecureToken createSecureToken();
    void saveSecureToken(SecureToken token);
    SecureToken findByToken(String token);
    void removeToken(SecureToken token);
    void removeTokenByToken(String token);
}
