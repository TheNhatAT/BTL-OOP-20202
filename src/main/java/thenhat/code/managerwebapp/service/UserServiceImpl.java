package thenhat.code.managerwebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thenhat.code.managerwebapp.DAO.UserDAO;
import thenhat.code.managerwebapp.model.Users;

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
    public Users saveUser(Users users) {
        userDAO.saveUser(users);
        return users;
    }

    @Override
    public Users findUserByEmail(String email) {
        Users users = userDAO.findUserByEmail(email);
        return users;
    }
}
