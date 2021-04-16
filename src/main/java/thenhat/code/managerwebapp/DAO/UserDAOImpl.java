package thenhat.code.managerwebapp.DAO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import thenhat.code.managerwebapp.model.User;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Slf4j
@Transactional
@Repository
public class UserDAOImpl implements UserDAO {
    //== field ==
    EntityManager em;

    //== methods ==
    @Override
    public User saveUser(User user) {
        log.info("start() save user into DB");
        em.persist(user);
        em.close();
        log.info("finish() save user into DB");
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        log.info("start() fine user from DB");
        User user = (User) em.createNativeQuery("select * from user where user.email = " + email).getSingleResult();
        log.info("finish() find user by email");
        return user;
    }
}
