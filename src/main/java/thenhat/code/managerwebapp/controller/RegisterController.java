package thenhat.code.managerwebapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;
import thenhat.code.managerwebapp.error.exception.InvalidTokenException;
import thenhat.code.managerwebapp.error.exception.UserAlreadyExistException;
import thenhat.code.managerwebapp.model.entity.Users;
import thenhat.code.managerwebapp.service.entity.UserService;
import thenhat.code.managerwebapp.util.ViewNames;

import javax.validation.Valid;

@Slf4j
@CrossOrigin("http://localhost:8080")
@Controller
@RequestMapping("/register")
public class RegisterController {

    //== field ==
    private final UserService userService;
    private final MessageSource messageSource;

    //== constructor injection ==
    @Autowired
    public RegisterController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    //== REST methods ==

    @GetMapping
    public String getRegistrationView(@ModelAttribute("user") Users user) {
        log.info("get registration form");
        return ViewNames.REGISTRATION_PAGE;
    }

    @GetMapping("/verify")
    public String verifyCustomer(@RequestParam(required = false) String token, final Model model, RedirectAttributes redirAttr) {
        if (StringUtils.isEmpty(token)) {
            //== addFlashAttribute in flash map (in user session) ==
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
    @PostMapping //== note: the BindingResult must be after model ==
    public String customerRegistration(@Valid @ModelAttribute("user") Users user, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        log.info("user form = {}", user.toString());
        if (result.hasErrors()) {
            return ViewNames.REGISTRATION_PAGE;
        }
        try {
            userService.register(user);
            redirectAttributes.addFlashAttribute("registeredUser", user);
        } catch (UserAlreadyExistException e) {
            model.addAttribute("registrationForm", user);
            return ViewNames.REGISTRATION_PAGE;
        }
        return "redirect:/" + ViewNames.LOGIN_PAGE;
    }
}
