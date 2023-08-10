package pl.dskrzyniarz.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dskrzyniarz.forum.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);

}
