package pl.dskrzyniarz.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.dskrzyniarz.forum.entity.Message;
import pl.dskrzyniarz.forum.entity.Topic;
import pl.dskrzyniarz.forum.entity.User;
import pl.dskrzyniarz.forum.repository.MessageRepository;
import pl.dskrzyniarz.forum.repository.TopicRepository;
import pl.dskrzyniarz.forum.service.MessageService;
import pl.dskrzyniarz.forum.service.TopicService;

import java.time.LocalDateTime;

@Controller
public class TopicController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private MessageService messageService;


    @GetMapping("/")
    public String allTopics(Model model){
        model.addAttribute("topics", topicService.getAllTopics());
        return "home";
    }
    @GetMapping("/{topicId}")
    public String showTopic(@PathVariable int topicId,
                                     Model model){

        Topic topic = topicService.getTopic(topicId);
        model.addAttribute("topic",topic);
        return "topic";
    }

    @GetMapping("/new")
    public String showTopicForm(Model model){
        model.addAttribute("topic", new Topic());
        model.addAttribute("message", new Message());
        return "topic-form";
    }

    @PostMapping("/new")
    public String saveTopic(@ModelAttribute Topic topic,
                            @ModelAttribute Message message,
                            Authentication authentication){
        topicService.saveTopic(topic);
        message.setTopic(topic);
        message.setDateCreated(LocalDateTime.now());
        message.setAuthor((User)authentication.getPrincipal());
        messageService.saveMessage(message);
        return "redirect:/" + topic.getId();
    }

    @GetMapping("/{topicId}/delete")
    public String deleteTopic(@PathVariable int topicId){
        messageService.deleteAllInTopic(topicService.getTopic(topicId));
        topicService.deleteTopic(topicId);
        return "redirect:/";
    }
    @GetMapping("/{topicId}/edit")
    public String showTopicEditForm(@PathVariable int topicId,
                              Model model){
        Topic existingTopic = topicService.getTopic(topicId);
        model.addAttribute("topic", existingTopic);
        return "topic-form-edit";
    }
    @PostMapping("/{topicId}/edit")
    public String updateTopic(@PathVariable int topicId,
                              @ModelAttribute Topic editedTopic){
        Topic existingTopic = topicService.getTopic(topicId);
        existingTopic.setTitle(editedTopic.getTitle());
        topicService.saveTopic(existingTopic);
        return "redirect:/" + topicId;
    }

}
