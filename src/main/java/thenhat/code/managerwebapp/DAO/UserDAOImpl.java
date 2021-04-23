package thenhat.code.managerwebapp.DAO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import thenhat.code.managerwebapp.model.Users;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Slf4j
@Transactional
@Repository
public class UserDAOImpl implements UserDAO {
    //== field ==
    @PersistenceContext
    EntityManager em;

    //== methods ==
    @Override
    public Users saveUser(Users user) {
        log.info("start() save user = {} into DB", user);
        em.persist(user);
        em.close();
        log.info("finish() save user into DB");
        return user;
    }

    public Users updateUser(Users user) {
        log.info("start() update user = {} into DB", user);
        em.merge(user);
        em.close();
        log.info("finish() update user into DB");
        return user;
    }
    @Override
    public Users findUserByEmail(String email) {
        log.info("start() find user from DB, email = {}", email);
        try {
            Users user = (Users) em.createNativeQuery("select * from users where email_address = " + "'" + email + "'", Users.class).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        Users user = (Users) em.createNativeQuery("select * from users where email_address = " + "'" + email + "'", Users.class).getSingleResult();
        log.info("finish() find user by email, user = {}", user);
        return user;
    }
}
