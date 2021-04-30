package thenhat.code.managerwebapp.DAO.security;

import thenhat.code.managerwebapp.model.entity.SecureToken;

public interface SecureTokenDAO {
    void saveSecureToken(SecureToken token);
    SecureToken findByToken(String token);
    void delete(SecureToken token);
    void removeByToken(String token);
}
