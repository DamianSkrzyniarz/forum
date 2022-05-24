package pl.dskrzyniarz.forum.post;

import pl.dskrzyniarz.forum.user.User;

import javax.persistence.*;

@Entity
public class Post {

    private @Id @GeneratedValue Long id;
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;
    private String body;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post() {
    }

    public Post(User user, String body) {
        this.user = user;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
