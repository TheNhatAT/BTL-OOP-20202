package thenhat.code.managerwebapp.DAO;

import thenhat.code.managerwebapp.model.SecureToken;

public interface SecureTokenDAO {
    void saveSecureToken(SecureToken token);
    SecureToken findByToken(String token);
    void delete(SecureToken token);
    void removeByToken(String token);
}
