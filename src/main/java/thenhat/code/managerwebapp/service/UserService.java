package thenhat.code.managerwebapp.service;

import thenhat.code.managerwebapp.model.User;

public interface UserService {
    User saveUser(User user);
    User findUserByEmail(String email);
}
