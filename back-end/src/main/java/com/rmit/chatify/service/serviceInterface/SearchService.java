package com.rmit.chatify.service.serviceInterface;

import com.rmit.chatify.dtos.ConversationDTO;
import com.rmit.chatify.dtos.MessageDTO;
import com.rmit.chatify.dtos.UserDTO;

import java.util.List;

public interface SearchService {
    List<ConversationDTO> getConversationByName(String word, int page, int size);

    List<MessageDTO> getMessageInConversation(Long conversation_id, String word, int page, int size);

    List<UserDTO> getUserBasedOnEmail(String email, int page, int size);

    List<UserDTO> getUserBasedOnName(String name, int page, int size);
}
