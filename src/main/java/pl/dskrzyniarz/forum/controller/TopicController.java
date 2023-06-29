package pl.dskrzyniarz.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.dskrzyniarz.forum.entity.Message;
import pl.dskrzyniarz.forum.entity.Topic;
import pl.dskrzyniarz.forum.repository.MessageRepository;
import pl.dskrzyniarz.forum.repository.TopicRepository;

import java.time.LocalDateTime;

@Controller
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private MessageRepository messageRepository;


    @GetMapping("/")
    public String readTopics(Model model){
        model.addAttribute("topics", topicRepository.findAll());
        return "home";
    }
    @GetMapping("/{topicId}")
    public String getMessagesInTopic(@PathVariable int topicId,
                                     Model model){

        Topic topic = topicRepository.findById(topicId).get();
        model.addAttribute("topic",topic);
        return "topic";
    }

    @GetMapping("/new")
    public String newTopicForm(Model model){
        model.addAttribute("topic", new Topic());
        model.addAttribute("message", new Message());
        return "topicform";
    }

    @PostMapping("/new")
    public String submitTopic(@ModelAttribute Topic topic,
                              @ModelAttribute Message message){
        topicRepository.save(topic);
        message.setTopic(topic);
        message.setDateCreated(LocalDateTime.now());
        messageRepository.save(message);
        return "redirect:/" + topic.getId();
    }

}
