package vn.codegym.simpledictionaryapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DictionaryController {

    // "Database" giả định bằng HashMap
    private static final Map<String, String> dictionary = new HashMap<>();

    static {
        dictionary.put("hello", "xin chào");//(key,value)
        dictionary.put("dog", "con chó");
        dictionary.put("cat", "con mèo");
        dictionary.put("book", "quyển sách");
        dictionary.put("computer", "máy tính");
    }

    // Trang chủ hiển thị form
    @GetMapping("/")
    public String home() {
        return "index"; // -> /WEB-INF/views/index.jsp
    }

    // Xử lý tra cứu từ
    @PostMapping("/translate")
    public String translate(@RequestParam("w") String word3, Model model) {
        String result = dictionary.get(word3.toLowerCase());


        if (result != null) {
            model.addAttribute("wr", word3);
            model.addAttribute("r", result);
        } else {
            model.addAttribute("wr", word3);
            model.addAttribute("r", "Không tìm thấy nghĩa cho từ này!");
        }

        return "result"; // -> /WEB-INF/views/result.jsp
    }
}