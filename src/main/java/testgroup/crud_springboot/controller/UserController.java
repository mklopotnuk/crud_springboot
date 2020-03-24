package testgroup.crud_springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping(value = "/admin")
    public String allUsers() {
        return "admin";
    }

    @GetMapping(value = "/user")
    public String currentUser() {
        return "user";
    }

    @GetMapping(value = "/login")
    public String loginPage(String logout, Model model) {
        if (logout != null) {
            model.addAttribute("message", "Logout successfully");
        }
        return "login";
    }
}
