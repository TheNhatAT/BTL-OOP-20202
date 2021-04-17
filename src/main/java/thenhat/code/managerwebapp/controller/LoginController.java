package thenhat.code.managerwebapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import thenhat.code.managerwebapp.model.Users;
import thenhat.code.managerwebapp.service.UserService;
import thenhat.code.managerwebapp.util.ViewNames;

@Slf4j
@Controller
public class LoginController {

    //== field ==
    private UserService userService;

    //== constructor injection ==
    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    //== REST methods ==
    @GetMapping("/welcome")
    public String welcome() {
        return ViewNames.WELCOME_PAGE;
    }

    @GetMapping("/register")
    public String getRegistrationView() {
        return ViewNames.REGISTRATION_PAGE;
    }

    @GetMapping("/registerCf")
    public String getRegistrationCfView() {
        return ViewNames.REGISTRATION_CONFIRMATION_PAGE;
    }

    @PostMapping("/register")
    public String customerRegistration(Users user) {
        log.info("user form = {}", user                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  );
        userService.saveUser(user);
        return "redirect:/login";
    }
}
