package pl.dskrzyniarz.forum.entity;

import jakarta.persistence.*;
import lombok.Data;

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
    private String body;
}
