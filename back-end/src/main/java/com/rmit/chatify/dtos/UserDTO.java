package com.rmit.chatify.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rmit.chatify.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.DocumentId;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Indexed
@Cacheable
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    //this is "primary key"
    @Id
    @Column
    @DocumentId
    @GeneratedValue(strategy = GenerationType.AUTO) //auto-increment
    private Long id;
    @Column(unique = true)
    @FullTextField
    private String username;
    @Column(unique = true)
    @FullTextField
    private String email;
    @Column
    @JsonIgnore
    private String password;
    @Column
    private String avatar;
    @Column
    private String status;
    @Column
    private Boolean isOnline;
    @Column
    private Boolean isDeactivated;
    //    @Column
//    private ArrayList<Long> convoList;


    //One user can have many messages
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
//    @IndexedEmbedded
    private List<MessageDTO> messages = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_notification", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "notification_id"))
    @JsonIgnore
    private Set<NotificationDTO> notifications = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_conversation", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "conversation_id"))
    @JsonIgnore
//    @IndexedEmbedded
    private List<ConversationDTO> conversations = new ArrayList<>();

    @OneToOne(mappedBy = "userDTO", cascade = CascadeType.ALL)
    private ProfileDTO profile;


    //User can have many friend
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_friend", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "friend_id"))
    @JsonIgnore
    //TODO: Discuss friend relationship again with Billie
    private List<FriendDTO> friends = new ArrayList<>();

    public static UserDTO from(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setStatus(user.getStatus());
        userDTO.setIsOnline(user.getIsOnline());
        userDTO.setIsDeactivated(false);
        return userDTO;
    }

    @Override
    public int hashCode() {
        return id.intValue();
    }

    @Override
    public boolean equals(Object obj) {
       //Equals code for UserDTO
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDTO other = (UserDTO) obj;
        return Objects.equals(this.id, other.id);
    }
}