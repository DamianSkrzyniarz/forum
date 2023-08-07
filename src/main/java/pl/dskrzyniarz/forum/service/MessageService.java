package pl.dskrzyniarz.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dskrzyniarz.forum.entity.Message;
import pl.dskrzyniarz.forum.entity.Topic;
import pl.dskrzyniarz.forum.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;


    public Message getMessage(int id){
        return messageRepository.findById(id).get();
    }

    public Message saveMessage(Message message){
        return messageRepository.save(message);
    }

    public void deleteMessage(int id){
        messageRepository.deleteById(id);
    }

    public Message editMessage(Message newMessage, int id){
        Message oldMessage = messageRepository.findById(id).get();
        oldMessage.setBody(newMessage.getBody());
        return messageRepository.save(oldMessage);
    }
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public List<Message> searchMessages(String searchedPhrase){
        return messageRepository.findByBodyContaining(searchedPhrase);
    }

    public void deleteAllInTopic(Topic topic){
        messageRepository.deleteAll(topic.getMessages());
    }
}
