package org.controller.login;

import jakarta.servlet.http.HttpSession;
<<<<<<< HEAD
import org.controller.login.models.LoginForm;
=======
import org.controller.login.model.LoginForm;
>>>>>>> 36adf264085266c22ae347d55343922053e8da7c
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

    @RequestMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model, HttpSession session) {
        if ("mohd".equals(loginForm.getUsername()) && "admin".equals(loginForm.getPassword())) {
            session.setAttribute("loggedIn", true);
            return "redirect:/success";
        } else {
            model.addAttribute("errorMessage", "Invalid username or password!");
            return "login";
        }
    }

    @RequestMapping("/success")
    public String success(Model model) {
        model.addAttribute("successMessage", "Login Successful!");
        return "success";
    }
}