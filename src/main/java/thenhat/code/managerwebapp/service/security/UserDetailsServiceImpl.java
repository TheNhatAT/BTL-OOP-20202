package thenhat.code.managerwebapp.service.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import thenhat.code.managerwebapp.model.entity.Users;
import thenhat.code.managerwebapp.service.entity.UserService;

@Slf4j
@Service("customUserService")
public class UserDetailsServiceImpl implements UserDetailsService {

    //== filed ==
    private UserService userService;

    //== constructor injection ==
    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    //== method ==
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username = {}", username);
        Users customer  = userService.findUserByEmail(username);
        if(customer  == null) {
            throw new UsernameNotFoundException(username);
        }
        //-- check account is verified --
        boolean enabled = !customer.isAccountVerified();
        //-- UserDetails store user info, allow non-security info (email, phone,...) --
        UserDetails user = User.withUsername(customer.getEmailAddress())
                .password(customer.getPassword())
                .disabled(enabled)
                .authorities("USER").build();
        return user;
    }
}
