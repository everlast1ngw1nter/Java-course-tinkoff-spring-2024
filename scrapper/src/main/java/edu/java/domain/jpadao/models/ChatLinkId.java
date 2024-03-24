package edu.java.domain.jpadao.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ChatLinkId implements Serializable {

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "link_id")
    private Long linkId;
}
