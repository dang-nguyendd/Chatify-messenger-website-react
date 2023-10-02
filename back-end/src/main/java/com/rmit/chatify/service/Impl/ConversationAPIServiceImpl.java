package com.rmit.chatify.service.Impl;

import com.rmit.chatify.dtos.ConversationDTO;
import com.rmit.chatify.dtos.MessageDTO;
import com.rmit.chatify.dtos.UserDTO;
import com.rmit.chatify.model.Conversation;
import com.rmit.chatify.model.ResponseObject;
import com.rmit.chatify.service.APIService;
import com.rmit.chatify.service.serviceInterface.ConversationService;
import com.rmit.chatify.service.serviceInterface.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConversationAPIServiceImpl extends APIService implements ConversationService {
    @Override
    public List<ConversationDTO> getAllConvos() {
        return conversationRepository.findAll();
    }

    @Autowired
    private MessageService messageService;

    @Override
    public ResponseEntity<ResponseObject> findById(Long id) {
        Optional<ConversationDTO> foundConversation = conversationRepository.findById(id);
        return foundConversation.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query Convo successfully", foundConversation)) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Cannot find Convo with id: ", ""));
    }

    @Override
    public ResponseEntity<ResponseObject> insertConvo(Conversation conversation) {
        ConversationDTO res = conversationRepository.save(ConversationDTO.from(conversation));
        //Verify and add user to the conversation
        Long[] participants = conversation.getParticipants().toArray(new Long[conversation.getParticipants().size()]);
        System.out.println("Participants: " + conversation.getParticipants());

        return addParticipants(participants, res);
    }

    private ResponseEntity<ResponseObject> addParticipants(Long[] participants, ConversationDTO res) {
        for (Long i : participants) {
            Optional<UserDTO> user = userRepository.findById(i);
            if (user.isPresent()) {
                System.out.println("User: " + i + user.get().getUsername());
                res.getParticipants().add(user.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Cannot find user with id: ", ""));
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query product successfully", conversationRepository.save(res)));

    }

    @Override
    public ResponseEntity<ResponseObject> updateConvo(Conversation newConversation, Long id) {
        ConversationDTO updatedConversationDTO = conversationRepository.findById(id).map(conversationDTO -> {
            conversationDTO.setConversationPhoto(newConversation.getConversationPhoto());
            return conversationRepository.save(conversationDTO);
        }).orElseGet(() -> {
            ConversationDTO newConversationDTO = ConversationDTO.from(newConversation);
            return conversationRepository.save(newConversationDTO);
        });
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Update user successfully", conversationRepository.save(updatedConversationDTO)));
    }

    @Override
    public ResponseEntity<ResponseObject> getConverSationByParticipantId(Long participantId, int page, int size) {
        //Get user info
        UserDTO user = userRepository.findById(participantId).orElseThrow(() -> new RuntimeException("Cannot find user with id: " + participantId));
        Page<ConversationDTO> conversationDTOPage = conversationRepository.getConversationByParticipantId(user, PageRequest.of(page, size));
        if(conversationDTOPage.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Cannot find conversation with user id: ", ""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query conversation successfully", conversationDTOPage));
    }

    @Override
    public ResponseEntity<ResponseObject> searchMessageByConversationID(Long id, String keyword, int page, int size) {
        //Search message by conversation id
        Page<MessageDTO> res = conversationRepository.searchMessageByConversation(id, keyword, PageRequest.of(page, size, Sort.by("createdAt").descending()));
        if (res.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Cannot find message with keyword: ", keyword));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query message successfully", res));
    }

}