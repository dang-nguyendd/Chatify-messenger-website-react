package com.rmit.chatify.socketService.Impl;

import com.rmit.chatify.dtos.MessageDTO;
import com.rmit.chatify.socketService.SocketService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MessageSocketServiceImpl extends SocketService implements com.rmit.chatify.socketService.socketServiceInterface.MessageSocketService {
    private final Map<String, List<MessageDTO>> messages = new ConcurrentHashMap<>();

    //When connect, load history message
    @Override
    public List<MessageDTO> getHistory(String room) {
        return messages.getOrDefault(room, Collections.emptyList());
    }

    //Get all of the messate
    @Override
    public Page<MessageDTO> getAllMessageByConversationid(Long id) {
        return messageRepository.getAllMessagesByConversationId(id, Pageable.unpaged());
    }

}