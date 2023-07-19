package pl.dskrzyniarz.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.dskrzyniarz.forum.entity.Message;
import pl.dskrzyniarz.forum.entity.Topic;

@Controller
public class UserController {

    @GetMapping("/login")
    public String showLoginForm(Model model){
        return "login-form";
    }


}
