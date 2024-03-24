package edu.java.domain.jpadao.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "chat_link")
public class ChatLink {

    @EmbeddedId
    private ChatLinkId id;

    @ManyToOne
    @MapsId("chatId")
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @ManyToOne
    @MapsId("linkId")
    @JoinColumn(name = "link_id")
    private Link link;
}
