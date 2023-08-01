package pl.dskrzyniarz.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.dskrzyniarz.forum.entity.Message;
import pl.dskrzyniarz.forum.entity.Topic;
import pl.dskrzyniarz.forum.repository.MessageRepository;
import pl.dskrzyniarz.forum.repository.TopicRepository;
import pl.dskrzyniarz.forum.service.MessageService;
import pl.dskrzyniarz.forum.service.TopicService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private TopicService topicService;

    @GetMapping("/{topicId}/new")
    public String showMessageForm(Model model,
                                 @PathVariable int topicId){
        model.addAttribute("message", new Message());
        Topic topic = topicService.getTopic(topicId);
        model.addAttribute("topic", topic);
        return "message-form";
    }
    @PostMapping("/{topicId}/new")
    public String saveMessage(@ModelAttribute Message message,
                                @PathVariable int topicId){

        Topic topic = topicService.getTopic(topicId);
        message.setTopic(topic);
        message.setDateCreated(LocalDateTime.now());
        messageService.saveMessage(message);
        return "redirect:/" + topic.getId();
    }

    @GetMapping("/message/{messageId}/delete")
    public String deleteMessage(@PathVariable int messageId){
        int topicId = messageService.getMessage(messageId).getTopic().getId();
        messageService.deleteMessage(messageId);
        Topic existingTopic = topicService.getTopic(topicId);
        if(existingTopic.getMessages().isEmpty()){ //delete topic if there's no other messages
            topicService.deleteTopic(topicId);
            return "redirect:/";
        }
        else {
            return "redirect:/" + existingTopic.getId();
        }
    }
    @GetMapping("/message/{messageId}/edit")
    public String showMessageEditForm(Model model,
                                  @PathVariable int messageId){
        Message existingMessage = messageService.getMessage(messageId);
        model.addAttribute("message", existingMessage);
        return "message-form-edit";
    }

    @PostMapping("/message/{messageId}/edit")
    public String updateMessage(@PathVariable int messageId,
                              @ModelAttribute Message editedMessage){
        Message originalMessage = messageService.editMessage(editedMessage, messageId);
        return "redirect:/" + originalMessage.getTopic().getId();
    }

    @PostMapping("/search")
    public String showSearchResults(Model model,
                                    @RequestParam String searchedPhrase){
        List<Message> messages = messageService.searchMessages(searchedPhrase);
        model.addAttribute("messages", messages);
        return "search-results";
    }

}
