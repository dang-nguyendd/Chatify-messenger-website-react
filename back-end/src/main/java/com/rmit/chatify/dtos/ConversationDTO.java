package com.rmit.chatify.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rmit.chatify.model.Conversation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "conversations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Indexed
@Cacheable
public class ConversationDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    //this is "primary key"
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO) //auto-increment
    @GenericField
    private Long id;
    @Column
    private String conversationPhoto = "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png";
    @Column
    @FullTextField
    private String conversationName;

    @Column
    private Boolean isBlocked = false;

    @Column
    private Boolean isGroup = true;

    @OneToMany(mappedBy = "conversationDTO", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @IndexedEmbedded
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MessageDTO> messages = new HashSet<>();

    //Conversation can have many user on board
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(
            name = "user_conversation",
            joinColumns = @JoinColumn(name = "conversation_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
//    @IndexedEmbedded
    private List<UserDTO> participants = new ArrayList<>();

    public static ConversationDTO from(Conversation conversation) {
        ConversationDTO conversationDTO = new ConversationDTO();
        conversationDTO.setConversationName(conversation.getConversationName());
        conversationDTO.setConversationPhoto(conversation.getConversationPhoto());
        return conversationDTO;
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
