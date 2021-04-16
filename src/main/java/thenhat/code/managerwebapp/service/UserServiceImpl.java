package thenhat.code.managerwebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thenhat.code.managerwebapp.DAO.UserDAO;
import thenhat.code.managerwebapp.model.User;

@Service
public class UserServiceImpl implements UserService{

    //== field ==
    UserDAO userDAO;

    //== constructor injection ==
    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    //== method ==
    @Override
    public User saveUser(User user) {
        userDAO.saveUser(user);
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userDAO.findUserByEmail(email);
        return user;
    }
}
