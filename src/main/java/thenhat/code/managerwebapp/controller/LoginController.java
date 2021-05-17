package thenhat.code.managerwebapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@CrossOrigin(origins = "http://localhost:8080") //-- for configuring allowed origins --
@RequestMapping
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/home")
    public String home(){
        return "welcome";
    }
}
