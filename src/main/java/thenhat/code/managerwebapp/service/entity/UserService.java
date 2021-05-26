package thenhat.code.managerwebapp.service.entity;

import thenhat.code.managerwebapp.error.exception.InvalidTokenException;
import thenhat.code.managerwebapp.error.exception.UserAlreadyExistException;
import thenhat.code.managerwebapp.model.entity.Users;

public interface UserService {
    Users saveUser(Users users);
    boolean verifyUser(final String token) throws InvalidTokenException;
    boolean checkIfUserExist(String email);
    Users findUserByEmail(String email);
    void register(Users user) throws UserAlreadyExistException;
    void sendRegistrationConfirmationEmail(Users user);
}
