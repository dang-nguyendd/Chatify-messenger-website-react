package com.rmit.chatify.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rmit.chatify.model.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Indexed
@Cacheable
public class MessageDTO implements Serializable {
    //this is "primary key"
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO) //auto-increment
    @DocumentId
    private Long id;
    @Column
    @FullTextField
    private String content;
    @Column
//    @SortableField

    @GenericField(sortable = Sortable.YES)
    private Timestamp timeStamp = Timestamp.from(Instant.now());
    ;
    @Column
    private Boolean isSeen;
    @Column
    private String mediaUrl = null;

    @Column
    private Long senderId;

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name="user_id")
//    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    //TODO: Retrieve only id ?? idk how
//    @IndexedEmbedded
    private UserDTO sender;

    @ManyToOne()
    @JoinColumn(name = "conversation_id")
    @JsonIgnore
    @IndexedEmbedded
    private ConversationDTO conversationDTO;


    public static MessageDTO from(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setSenderId(message.getSenderId());
        messageDTO.setContent(message.getContent());
        messageDTO.setIsSeen(message.getIsSeen());
        messageDTO.setMediaUrl(message.getMediaUrl());
        return messageDTO;
    }

    @Override
    public int hashCode() {
        return id.intValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return false;
    }
}