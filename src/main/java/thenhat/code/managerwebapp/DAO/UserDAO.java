package thenhat.code.managerwebapp.DAO;

import thenhat.code.managerwebapp.model.Users;

public interface UserDAO {
    Users saveUser(Users users);
    public Users updateUser(Users user);
    Users findUserByEmail(String email);
}
