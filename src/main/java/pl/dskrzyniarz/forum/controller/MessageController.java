package pl.dskrzyniarz.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.dskrzyniarz.forum.entity.Message;
import pl.dskrzyniarz.forum.entity.Topic;
import pl.dskrzyniarz.forum.repository.MessageRepository;
import pl.dskrzyniarz.forum.repository.TopicRepository;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/{topicId}/new")
    public String showMessageForm(Model model,
                                 @PathVariable int topicId){
        model.addAttribute("message", new Message());
        Topic topic = topicRepository.findById(topicId).get();
        model.addAttribute("topic", topic);
        return "message-form";
    }
    @PostMapping("/{topicId}/new")
    public String saveMessage(@ModelAttribute Message message,
                                @PathVariable int topicId){

        Topic topic = topicRepository.findById(topicId).get();
        message.setTopic(topic);
        message.setDateCreated(LocalDateTime.now());
        messageRepository.save(message);
        return "redirect:/" + topic.getId();
    }

    @GetMapping("/message/{messageId}/delete")
    public String deleteMessage(@PathVariable int messageId){
        Message existingMessage = messageRepository.findById(messageId).get();
        int topicId = existingMessage.getTopic().getId();
        messageRepository.delete(existingMessage);
        Topic existingTopic = topicRepository.findById(topicId).get();
        if(existingTopic.getMessages().size()==0){ //delete topic if there's no other messages
            topicRepository.delete(existingTopic);
            return "redirect:/";
        }
        else {
            return "redirect:/" + existingTopic.getId();
        }
    }
    @GetMapping("/message/{messageId}/edit")
    public String showMessageEditForm(Model model,
                                  @PathVariable int messageId){
        Message existingMessage = messageRepository.findById(messageId).get();
        model.addAttribute("message", existingMessage);
        return "message-form-edit";
    }

    @PostMapping("/message/{messageId}/edit")
    public String updateMessage(@PathVariable int messageId,
                              @ModelAttribute Message editedMessage){
        Message existingMessage = messageRepository.findById(messageId).get();
        existingMessage.setBody(editedMessage.getBody());
        messageRepository.save(existingMessage);
        return "redirect:/" + existingMessage.getTopic().getId();
    }

    @PostMapping("/search")
    public String showSearchResults(Model model,
                                    @RequestParam String searchedPhrase){
        List<Message> messages = messageRepository.findByBodyContaining(searchedPhrase);
        model.addAttribute("messages", messages);
        return "search-results";
    }

}
