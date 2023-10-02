package com.rmit.chatify.repository;

import com.rmit.chatify.dtos.MessageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<MessageDTO, Long> {
    @Query("SELECT f FROM MessageDTO f WHERE f.conversationDTO.id = ?1")
    Page<MessageDTO> getAllMessagesByConversationId(Long conversationId, Pageable pageable);
}
