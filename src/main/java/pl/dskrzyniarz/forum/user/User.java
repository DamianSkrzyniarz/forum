package pl.dskrzyniarz.forum.user;

import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class User {


    private @Id @GeneratedValue Long id;
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
