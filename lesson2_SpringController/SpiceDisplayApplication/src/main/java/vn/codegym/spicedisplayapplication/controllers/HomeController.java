package vn.codegym.spicedisplayapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    //mapping với HTTP request. Method: GET/POST/PUT/DELETE/...
    @GetMapping("/home")
    public String getHome() {
        return "home";
    }

    @PostMapping("/save")
    public String saveCondiments(@RequestParam("condiment") List<String> condiments,
                                 Model model) {
        model.addAttribute("condiments", condiments);
        return "result";
    }
}