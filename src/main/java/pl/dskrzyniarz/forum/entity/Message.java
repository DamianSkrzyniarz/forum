package pl.dskrzyniarz.forum.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name="TOPIC_ID", referencedColumnName="ID")
    private Topic topic;
    @Column(length=1024)
    @NotEmpty
    private String body;
    private LocalDateTime dateCreated;
    @ManyToOne
    private User author;
}
