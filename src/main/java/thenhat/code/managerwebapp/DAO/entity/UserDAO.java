package thenhat.code.managerwebapp.DAO.entity;

import thenhat.code.managerwebapp.model.entity.Users;

public interface UserDAO {
    Users saveUser(Users users);
    public Users updateUser(Users user);
    Users findUserByEmail(String email);
}
