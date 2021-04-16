package thenhat.code.managerwebapp.DAO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import thenhat.code.managerwebapp.model.Users;

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
    public Users saveUser(Users users) {
        log.info("start() save user = {} into DB", users);
        em.persist(users);
        em.close();
        log.info("finish() save user into DB");
        return users;
    }

    @Override
    public Users findUserByEmail(String email) {
        log.info("start() fine user from DB");
        Users users = (Users) em.createNativeQuery("select * from users where users.email_address = " + email).getSingleResult();
        log.info("finish() find user by email");
        return users;
    }
}
