package pl.dskrzyniarz.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.dskrzyniarz.forum.entity.Message;
import pl.dskrzyniarz.forum.entity.Topic;
import pl.dskrzyniarz.forum.repository.MessageRepository;
import pl.dskrzyniarz.forum.repository.TopicRepository;

@Controller
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/message/all")
    public String getAllMessages(Model model){
        model.addAttribute("messages", messageRepository.findAll());
        return "messages";
    }

    @GetMapping("/{topicId}/new")
    public String newMessageForm(Model model,
                                 @PathVariable int topicId){
        model.addAttribute("message", new Message());
        Topic topic = topicRepository.findById(topicId).get();
        model.addAttribute("topic", topic);
        return "messageform";
    }

    @PostMapping("/{topicId}/new")
    public String submitMessage(@ModelAttribute Message message,
                                @PathVariable int topicId){

        Topic topic = topicRepository.findById(topicId).get();
        message.setTopic(topic);
        messageRepository.save(message);
        return "success";
    }

}
