package thenhat.code.managerwebapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thenhat.code.managerwebapp.model.entity.Users;
import thenhat.code.managerwebapp.service.entity.UserService;

@Slf4j
@Controller
@CrossOrigin(origins = "http://localhost:8080") //-- for configuring allowed origins --
@RequestMapping
public class LoginController {
    //== field ==
    UserService userService;

    //== constructor injection
    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/home")
    public String home(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        log.info("username = {}", username);
        Users user = userService.findUserByEmail(username);
        user.setFullName(user.getFirstName() + " " + user.getLastName());
        if (user.getEmailAddress().equals("nhat.nt194347@sis.hust.edu.vn"))
            user.setRole("ADMIN");
        model.addAttribute("user", user);
        return "fe/home";
    }

    @GetMapping
    public String defaultPage(){
        return "redirect:/login";
    }
}
