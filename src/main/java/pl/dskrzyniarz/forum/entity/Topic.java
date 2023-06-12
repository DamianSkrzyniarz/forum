package pl.dskrzyniarz.forum.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Topic {
    @Id
    private int id;
    private String title;
    @OneToMany(mappedBy="topic")
    private List<Message> messages;
}
