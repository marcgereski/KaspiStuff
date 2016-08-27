package kz.kaspi.stuff.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping("data")
    public String user(Model model) throws IOException {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", "Viking");
        model.addAttribute("user", map);
        return "views/data";
    }
}
