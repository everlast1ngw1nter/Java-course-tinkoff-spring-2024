package edu.java.domain.jpadao;

import edu.java.domain.jpadao.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaChatDao extends JpaRepository<Chat, Long> {

    void deleteChatById(long chatId);
}
