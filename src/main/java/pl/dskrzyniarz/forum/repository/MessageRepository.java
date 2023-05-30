package pl.dskrzyniarz.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dskrzyniarz.forum.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
