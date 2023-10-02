package com.rmit.chatify.socketService.socketServiceInterface;

import com.rmit.chatify.dtos.MessageDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MessageSocketService {
    //When connect, load history message
    List<MessageDTO> getHistory(String room);

    //Get all of the messate
    Page<MessageDTO> getAllMessageByConversationid(Long id);
}
