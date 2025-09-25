package vn.codegym.personalcomputer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ComputerController {
    @GetMapping("/caculator")
    public String getCaculator() {
        return "caculator";
    }

    @PostMapping("/caculator")
    public String Caculator(@RequestParam("numberOne") double numberOne,
                            @RequestParam("numberTwo") double numberTwo,
                            @RequestParam("operator") String operator,
                            Model model) {
        String resultMessage = "";

        switch (operator) {
            case "add":
                resultMessage = "Addition: " + (numberOne + numberTwo);
                break;
            case "sub":
                resultMessage = "Subtraction: " + (numberOne - numberTwo);
                break;
            case "mul":
                resultMessage = "Multiplication: " + (numberOne * numberTwo);
                break;
            case "div":
                if (numberTwo != 0) {
                    resultMessage = "Division: " + (numberOne / numberTwo);
                } else {
                    resultMessage = ": Không thể chia cho 0";
                }
                break;
        }

        model.addAttribute("resultMessage", resultMessage);
        model.addAttribute("numberOne", numberOne);
        model.addAttribute("numberTwo", numberTwo);

        return "caculator";
    }

}
