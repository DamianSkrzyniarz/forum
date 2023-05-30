package pl.dskrzyniarz.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.dskrzyniarz.forum.entity.Message;
import pl.dskrzyniarz.forum.repository.MessageRepository;

@Controller
public class MessageController {

    @Autowired
    MessageRepository repository;

    @GetMapping("/message/all")
    public String getAllMessages(Model model){
        model.addAttribute("messages", repository.findAll());
        return "messages";
    }

    @GetMapping("message/new")
    public String newMessageForm(Model model){
        model.addAttribute("message", new Message());
        return "messageform";
    }

    @PostMapping("/message/new")
    public String submitMessage(@ModelAttribute Message message){
        repository.save(message);
        return "success";
    }

}
