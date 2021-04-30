package thenhat.code.managerwebapp.service.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;
import thenhat.code.managerwebapp.DAO.security.SecureTokenDAO;
import thenhat.code.managerwebapp.model.entity.SecureToken;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
@Slf4j
public class SecureTokenServiceImpl implements SecureTokenService {

    //== fields ==
    private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom(15);
    private static final Charset US_ASCII = StandardCharsets.US_ASCII;

    @Value("${jdj.secure.token.validity}")
    private int tokenValidityInSeconds;
    private SecureTokenDAO secureTokenDAO;

    //== constructor injection ==
    @Autowired
    public SecureTokenServiceImpl(SecureTokenDAO secureTokenDAO) {
        this.secureTokenDAO = secureTokenDAO;
    }

    //== methods ==
    @Override
    public SecureToken createSecureToken() {
        String tokenValue = new String(Base64.encodeBase64URLSafe(DEFAULT_TOKEN_GENERATOR.generateKey()), US_ASCII); // this is a sample, you can adapt as per your security need
        SecureToken secureToken = new SecureToken();
        secureToken.setToken(tokenValue);
        secureToken.setExpireAt(LocalDateTime.now().plusSeconds(getTokenValidityInSeconds()));
        this.saveSecureToken(secureToken);
        return secureToken;
    }

    @Override
    public void saveSecureToken(SecureToken token) {
        secureTokenDAO.saveSecureToken(token);
    }

    @Override
    public SecureToken findByToken(String token) {
        return secureTokenDAO.findByToken(token);
    }

    @Override
    public void removeToken(SecureToken token) {
        secureTokenDAO.delete(token);
    }

    @Override
    public void removeTokenByToken(String token) {
        secureTokenDAO.removeByToken(token);
    }

    public int getTokenValidityInSeconds() {
        return tokenValidityInSeconds;
    }
}
