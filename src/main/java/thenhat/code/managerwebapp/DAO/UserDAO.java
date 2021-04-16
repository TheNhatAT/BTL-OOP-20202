package thenhat.code.managerwebapp.DAO;

import thenhat.code.managerwebapp.model.User;

public interface UserDAO {
    User saveUser(User user);
    User findUserByEmail(String email);
}
