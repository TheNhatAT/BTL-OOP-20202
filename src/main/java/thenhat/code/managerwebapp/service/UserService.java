package thenhat.code.managerwebapp.service;

import thenhat.code.managerwebapp.model.Users;

public interface UserService {
    Users saveUser(Users users);
    Users findUserByEmail(String email);
}
