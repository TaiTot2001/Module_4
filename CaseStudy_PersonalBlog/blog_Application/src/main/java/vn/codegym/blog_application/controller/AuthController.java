package vn.codegym.blog_application.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.codegym.blog_application.model.User;
import vn.codegym.blog_application.service.UserService;

import java.util.Locale;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model,
                        Locale locale) {

        if (error != null) {
            String msg = messageSource.getMessage("login.error", null, locale);
            model.addAttribute("errorMessage", msg);
        }

        if (logout != null) {
            String msg = messageSource.getMessage("login.logout", null, locale);
            model.addAttribute("logoutMessage", msg);
        }

        return "login";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        userService.save(user);
        return "redirect:/auth/login";
    }
}