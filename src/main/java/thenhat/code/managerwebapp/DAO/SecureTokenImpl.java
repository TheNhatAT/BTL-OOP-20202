package thenhat.code.managerwebapp.DAO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thenhat.code.managerwebapp.model.SecureToken;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@Repository
@Transactional
public class SecureTokenImpl implements SecureTokenDAO{

    //== field ==
    @PersistenceContext
    EntityManager em;

    @Override
    public void saveSecureToken(SecureToken token) {
        log.info("start() save token = {}", token.toString());
        em.persist(token);
        em.close();
        log.info("finish save token");
    }

    @Override
    public SecureToken findByToken(String token) {
        log.info("start() find token");
        SecureToken findToken = (SecureToken) em.createNativeQuery("SELECT * FROM secure_token WHERE token = " + "'" + token + "'", SecureToken.class).getSingleResult();
        log.info("finish() find token");
        em.close();
        return findToken;
    }

    @Override
    public void delete(SecureToken token) {
        log.info("start() delete token");
        em.createNativeQuery("DELETE FROM secure_token WHERE token =" + token.getToken());
        log.info("finish() delete token");
        em.close();
    }

    @Override
    public void removeByToken(String token) {
        log.info("start() delete token");
        em.createNativeQuery("DELETE FROM secure_token WHERE token = " + "'" + token + "'");
        log.info("finish() delete token");
        em.close();
    }
}
