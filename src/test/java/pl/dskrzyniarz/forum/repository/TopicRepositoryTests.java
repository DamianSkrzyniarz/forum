package pl.dskrzyniarz.forum.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.dskrzyniarz.forum.entity.Topic;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TopicRepositoryTests {

    @Autowired
    private TopicRepository topicRepository;

    @Test
    public void Save_ReturnsSavedTopic(){
        Topic testTopic = new Topic();
        testTopic.setTitle("Test title");

        Topic savedTopic = topicRepository.save(testTopic);

        Assertions.assertNotNull(savedTopic);
        Assertions.assertTrue(testTopic.getId()>0);
    }

    @Test
    public void FindAll_ReturnsMultipleTopics(){
        Topic firstTopic = new Topic();
        firstTopic.setTitle("first title");
        topicRepository.save(firstTopic);
        Topic secondTopic = new Topic();
        secondTopic.setTitle("second title");
        topicRepository.save(secondTopic);

        List<Topic> topicList =  topicRepository.findAll();

        Assertions.assertNotNull(topicList);
        Assertions.assertTrue(topicList.size()>1);
    }

    @Test
    public void FindById_ReturnsTopic(){
        Topic topic = new Topic();
        topic.setTitle("Test title");
        topicRepository.save(topic);

        Topic returnedTopic = topicRepository.findById(topic.getId()).get();

        Assertions.assertNotNull(returnedTopic);
        Assertions.assertEquals(topic.getId(), returnedTopic.getId());
    }

    @Test
    public void DeleteById_ReturnsEmptyOptional(){
        Topic topic = new Topic();
        topic.setTitle("title");
        topicRepository.save(topic);

        topicRepository.deleteById(topic.getId());

        Assertions.assertFalse(topicRepository.findById(topic.getId()).isPresent());
    }
}
