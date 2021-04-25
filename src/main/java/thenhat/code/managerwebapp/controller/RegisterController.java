package thenhat.code.managerwebapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;
import thenhat.code.managerwebapp.exception.InvalidTokenException;
import thenhat.code.managerwebapp.exception.UserAlreadyExistException;
import thenhat.code.managerwebapp.model.Users;
import thenhat.code.managerwebapp.service.UserService;
import thenhat.code.managerwebapp.util.ViewNames;

@Slf4j
@CrossOrigin("http://localhost:8080")
@Controller
@RequestMapping("/register")
public class RegisterController {

    //== field ==
    private UserService userService;
    private MessageSource messageSource;

    //== constructor injection ==
    @Autowired
    public RegisterController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    //== REST methods ==
    @GetMapping("/welcome")
    public String welcome() {
        return ViewNames.WELCOME_PAGE;
    }

    @GetMapping("")
    public String getRegistrationView(@ModelAttribute("user") Users user) {
        return ViewNames.REGISTRATION_PAGE;
    }

    @GetMapping("/confirm")
    public String getRegistrationCfView() {
        return ViewNames.REGISTRATION_CONFIRMATION_PAGE;
    }

    @GetMapping("/verify")
    public String verifyCustomer(@RequestParam(required = false) String token, final Model model, RedirectAttributes redirAttr) {
        if (StringUtils.isEmpty(token)) {
            //== addFlashAttribute in flashmap (in user session) ==
            redirAttr.addFlashAttribute("tokenError", messageSource.
                    getMessage("user.registration.verification.missing.token", null, LocaleContextHolder.getLocale()));
            return "redirect:/" + ViewNames.LOGIN_PAGE;
        }
        try {
            userService.verifyUser(token);
        } catch (InvalidTokenException e) {
            redirAttr.addFlashAttribute("tokenError", messageSource.
                    getMessage("user.registration.verification.invalid.token", null, LocaleContextHolder.getLocale()));
            return "redirect:/" + ViewNames.LOGIN_PAGE;
        }

        redirAttr.addFlashAttribute("verifiedAccountMsg", messageSource.
                getMessage("user.registration.verification.success", null, LocaleContextHolder.getLocale()));
        return "redirect:/" + ViewNames.LOGIN_PAGE;

    }
    @PostMapping("")
    public String customerRegistration(Users user, Model model) {
        log.info("user form = {}", user);
        try {
            userService.register(user);
        } catch (UserAlreadyExistException e) {
            model.addAttribute("registrationForm", user);
            return "";
        }
        model.addAttribute("registrationMsg", messageSource.getMessage("user.registration.verification.email.msg", null, LocaleContextHolder.getLocale()));
        return "redirect:/login";
    }
}
