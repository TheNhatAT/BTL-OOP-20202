package thenhat.code.managerwebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import thenhat.code.managerwebapp.model.Users;

@Service("customUserService")
public class CustomUserService implements UserDetailsService {

    //== filed ==
    private  UserService userService;

    //== constructor injection ==
    @Autowired
    public CustomUserService(UserService userService) {
        this.userService = userService;
    }

    //== method ==
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userService.findUserByEmail(username);
        if(users == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(users.getEmailAddress()).password(users.getPassword()).authorities("USER").build();
        return userDetails;
    }
}
