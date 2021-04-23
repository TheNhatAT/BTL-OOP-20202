package thenhat.code.managerwebapp.service.security;

import org.springframework.security.core.token.Token;
import thenhat.code.managerwebapp.model.SecureToken;

public interface SecureTokenService {

    SecureToken createSecureToken();
    void saveSecureToken(SecureToken token);
    SecureToken findByToken(String token);
    void removeToken(SecureToken token);
    void removeTokenByToken(String token);
}
