package org.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @RequestMapping("/spring")
    public String sayHello(Model model) {
        model.addAttribute("message","Hello from Spring MVC");
        return "index";
    }
}