package thenhat.code.managerwebapp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

    //== field ==
    @Resource(name = "customUserService")
    private UserDetailsService userDetailsService;

    //== method ==
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //== for real ==
//        http.csrf().disable()
//                .authorizeRequests().antMatchers("/login", "/register")
//                .permitAll()
//                .antMatchers("/api/**", "/home")
//                .hasAuthority("ADMIN")
//                .and().formLogin()
//                .defaultSuccessUrl("/home", true)
//                .loginPage("/login") // default is /login with an HTTP get
//                .failureUrl("/login?error=true");

        //== for test API ==
        http.csrf().disable()
                .authorizeRequests().anyRequest().permitAll();
    }


    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }
}
