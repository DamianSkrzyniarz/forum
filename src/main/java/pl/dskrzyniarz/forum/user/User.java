package pl.dskrzyniarz.forum.user;

import pl.dskrzyniarz.forum.post.Post;

import javax.persistence.*;

import java.util.List;

@Entity
public class User {


    private @Id @GeneratedValue Long id;
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

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
