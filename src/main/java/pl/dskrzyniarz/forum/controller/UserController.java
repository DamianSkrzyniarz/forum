package pl.dskrzyniarz.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.dskrzyniarz.forum.dto.UserDto;
import pl.dskrzyniarz.forum.entity.Message;
import pl.dskrzyniarz.forum.entity.Topic;
import pl.dskrzyniarz.forum.service.UserService;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String showLoginForm(Model model){
        return "login-form";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register-form";
    }

    @PostMapping("/register")
    public String saveUser(Model model, @ModelAttribute UserDto userDto) {
        userService.saveUser(userDto);
        return "redirect:/";
    }


}
