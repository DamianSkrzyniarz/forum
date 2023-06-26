package pl.dskrzyniarz.forum.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    @OneToMany(mappedBy="topic")
    private List<Message> messages;

    public void addMessage(Message message){
        messages.add(message);
    }
}
