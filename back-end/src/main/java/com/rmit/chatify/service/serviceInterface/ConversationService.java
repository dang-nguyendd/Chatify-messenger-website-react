package com.rmit.chatify.service.serviceInterface;

import com.rmit.chatify.dtos.ConversationDTO;
import com.rmit.chatify.model.Conversation;
import com.rmit.chatify.model.ResponseObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ConversationService {
    //Pagination
    List<ConversationDTO> getAllConvos();

    ResponseEntity<ResponseObject> findById(Long id);

    ResponseEntity<ResponseObject> insertConvo(Conversation newConversation);

    ResponseEntity<ResponseObject> updateConvo(Conversation newConversation, Long id);


    //Get converstaion
    ResponseEntity<ResponseObject> getConverSationByParticipantId(Long participantId, int page, int size);

    ResponseEntity<ResponseObject> searchMessageByConversationID(Long id, String keyword, int page, int size);
}
