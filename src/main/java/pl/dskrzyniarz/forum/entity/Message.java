package pl.dskrzyniarz.forum.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String body;
}
