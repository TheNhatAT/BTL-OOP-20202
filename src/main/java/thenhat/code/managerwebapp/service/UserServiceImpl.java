package thenhat.code.managerwebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import thenhat.code.managerwebapp.DAO.UserDAO;
import thenhat.code.managerwebapp.model.Users;

@Service
public class UserServiceImpl implements UserService{

    //== fields ==
    UserDAO userDAO;
    PasswordEncoder passwordEncoder;

    //== constructor injection ==
    @Autowired
    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    //== method ==
    @Override
    public Users saveUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.saveUser(user);
        return user;
    }

    @Override
    public Users findUserByEmail(String email) {
        Users user = userDAO.findUserByEmail(email);
        return user;
    }
}
