//package thenhat.code.managerwebapp.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityWebConfig extends WebSecurityConfigurerAdapter {
//
//    //== config for security ==\\
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests().anyRequest().authenticated()
//                .and().httpBasic();
//
//    }
//
//    //== config admin account ==\\
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder authentication)
//            throws Exception
//    {
//        authentication.inMemoryAuthentication()
//                .withUser("admin")
//                .password(passwordEncoder().encode("admin"))
//                .authorities("ROLE_USER");
//    }
//
//    //-- tim hieu them --\\
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
