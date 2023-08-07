package pl.dskrzyniarz.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dskrzyniarz.forum.entity.Topic;
import pl.dskrzyniarz.forum.repository.TopicRepository;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;


    public Topic getTopic(int id){
        return topicRepository.findById(id).get();
    }
    public Topic saveTopic(Topic topic){
        return topicRepository.save(topic);
    }

    public void deleteTopic(int id){
        topicRepository.deleteById(id);
    }

    public Topic editTopic(Topic newTopic, int id){
        Topic oldTopic = topicRepository.findById(id).get();
        oldTopic.setTitle(newTopic.getTitle());
        return topicRepository.save(oldTopic);
    }

    public List<Topic> getAllTopics(){
        return topicRepository.findAll();
    }
}
