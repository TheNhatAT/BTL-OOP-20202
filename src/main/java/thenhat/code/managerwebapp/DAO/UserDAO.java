package thenhat.code.managerwebapp.DAO;

import thenhat.code.managerwebapp.model.Users;

public interface UserDAO {
    Users saveUser(Users users);
    Users findUserByEmail(String email);
}
