package com.rmit.chatify.service.serviceInterface;

import com.rmit.chatify.dtos.ConversationDTO;
import com.rmit.chatify.dtos.MessageDTO;
import com.rmit.chatify.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageService {
    List<MessageDTO> getAllMessages();

    MessageDTO findById(Long id);

    //Get all message by conversation id
    Page<MessageDTO> getAllMessagesByConversationId(Long conversationId, int page, int size);
    ConversationDTO insertMessage(Long id, Message message);

    //Get all message by conversation id
    Page<MessageDTO> getAllMessagesByConversationId(Long conversationId, Pageable pageable);

}
