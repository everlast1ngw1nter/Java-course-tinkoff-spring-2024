package edu.java.domain.jpadao;

import edu.java.domain.jpadao.models.Link;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaLinkDao extends JpaRepository<Link, Long> {

    @Query("SELECT cl.link FROM ChatLink cl WHERE cl.chat.id = :chatId")
    List<Link> findAllByChatId(@Param("chatId") long chatId);

    @Query("SELECT cl.link, cl.chat FROM ChatLink cl WHERE cl.link.lastCheckTime < :staleTime")
    List<Object[]> findAllStaleLinks(@Param("staleTime") Timestamp staleTime);

    @Query("UPDATE Link l SET l.lastCheckTime = CURRENT_TIMESTAMP WHERE l.id = :linkId")
    void updateCheckTime(@Param("linkId") long linkId);
}
