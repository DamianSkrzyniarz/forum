package pl.dskrzyniarz.forum.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotEmpty
    private String title;
    @OneToMany(mappedBy="topic")
    private List<Message> messages;

    public LocalDateTime getLastMessageDate(){
        return messages.get(messages.size()-1).getDateCreated();
    }
}
