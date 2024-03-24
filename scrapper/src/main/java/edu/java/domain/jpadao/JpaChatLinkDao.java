package edu.java.domain.jpadao;

import edu.java.domain.jpadao.models.ChatLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaChatLinkDao extends JpaRepository<ChatLink, Long> {
    void deleteByLinkIdAndChatId(Long linkId, Long chatId);
}
