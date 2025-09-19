package vn.codegym.demo4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    //mapping với HTTP request. Method: GET/POST/PUT/DELETE/...
    @GetMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("title", "My Home");
        return "home";
    }
}













