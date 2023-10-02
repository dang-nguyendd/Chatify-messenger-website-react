package com.rmit.chatify.repository;

import com.rmit.chatify.dtos.ConversationDTO;
import com.rmit.chatify.dtos.FriendDTO;
import com.rmit.chatify.dtos.MessageDTO;
import com.rmit.chatify.dtos.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ConversationRepository extends JpaRepository<ConversationDTO, Long> {
    //Find conversation by participant id
    @Query("SELECT f FROM ConversationDTO f WHERE ?1 member of f.participants and ?2 member of f.participants and f.isGroup = false")
    List<ConversationDTO> findFriendConversationGroupByUsers(UserDTO user1, UserDTO user2);

    //Get all message of conversation


    //Get conversation by participant id
    @Query("SELECT f FROM ConversationDTO f WHERE ?1 member of f.participants")
    Page<ConversationDTO> getConversationByParticipantId(UserDTO user, Pageable pageable);

    @Query(value = "SELECT m FROM messages m WHERE m.conversation_id = ?1 AND m.content = ?2", nativeQuery = true)
    Page<MessageDTO> searchMessageByConversation(Long conversationId, String keyword, Pageable pageable);
}
