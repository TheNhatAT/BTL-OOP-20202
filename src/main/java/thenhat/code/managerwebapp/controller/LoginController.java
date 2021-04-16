package thenhat.code.managerwebapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thenhat.code.managerwebapp.model.User;
import thenhat.code.managerwebapp.service.UserService;
import thenhat.code.managerwebapp.util.ViewNames;

@Slf4j
@Controller
@CrossOrigin("http://localhost:8080") //-- for configuring allowed origins --
public class LoginController {

    //== field ==
    private UserService userService;
    private ViewNames viewNames = new ViewNames();

    //== constructor injection ==
    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    //== REST methods ==
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/register")
    public String getRegistrationView(){
        return "registration";
    }

    @PostMapping("/register")
    public String customerRegistration(@ModelAttribute("user") User user, Model model){
        userService.saveUser(user);
        return viewNames.REGISTRATION_CONFIRMATION_PAGE;
    }
}
