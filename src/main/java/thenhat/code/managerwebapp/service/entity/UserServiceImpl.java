package thenhat.code.managerwebapp.service.entity;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import thenhat.code.managerwebapp.DAO.entity.UserDAO;
import thenhat.code.managerwebapp.error.exception.InvalidTokenException;
import thenhat.code.managerwebapp.error.exception.UserAlreadyExistException;
import thenhat.code.managerwebapp.model.entity.SecureToken;
import thenhat.code.managerwebapp.model.entity.Users;
import thenhat.code.managerwebapp.model.email.AccountVerificationEmailContext;
import thenhat.code.managerwebapp.service.email.EmailService;
import thenhat.code.managerwebapp.service.security.SecureTokenService;

import javax.mail.MessagingException;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService{

    //== fields ==
    UserDAO userDAO;
    PasswordEncoder passwordEncoder;
    SecureTokenService secureTokenService;
    EmailService emailService;

    @Value("${site.base.url.http}")
    private String baseUrl;

    //== constructor injection ==
    @Autowired
    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder, SecureTokenService secureTokenService, EmailService emailService) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        this.secureTokenService = secureTokenService;
        this.emailService = emailService;
    }

    //== method ==
    @Override
    public Users saveUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.saveUser(user);
        return user;
    }

    @Override
    public boolean verifyUser(String token) throws InvalidTokenException {
        SecureToken secureToken = secureTokenService.findByToken(token);
        if(Objects.isNull(secureToken) || !StringUtils.equals(token, secureToken.getToken()) || secureToken.isExpired()) {
            throw new InvalidTokenException("Token is not valid");
        }
        Users user = userDAO.findUserByEmail(secureToken.getUser().getEmailAddress());
        if(Objects.isNull(user)){
            return false;
        }
        user.setAccountVerified(true);
        userDAO.updateUser(user);
        //== remove invalid token
        secureTokenService.removeToken(secureToken);
        return false;
    }

    @Override
    public boolean checkIfUserExist(String email) {
        return userDAO.findUserByEmail(email) != null ? true : false;
    }

    @Override
    public Users findUserByEmail(String email) {
        Users user = userDAO.findUserByEmail(email);
        return user;
    }

    @Override
    public void register(Users user) throws UserAlreadyExistException {
        if(checkIfUserExist(user.getEmailAddress())) {
            throw new UserAlreadyExistException("This email be used!!!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.saveUser(user);
        sendRegistrationConfirmationEmail(user);
    }

    @Override
    public void sendRegistrationConfirmationEmail(Users user) {
        SecureToken secureToken = secureTokenService.createSecureToken();
        secureToken.setUser(user);
        secureTokenService.saveSecureToken(secureToken);
        AccountVerificationEmailContext emailContext = new AccountVerificationEmailContext();
        emailContext.init(user);
        emailContext.setToken(secureToken.getToken());
        emailContext.buildVerificationUrl(baseUrl, secureToken.getToken());
        try {
            emailService.sendEmail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
