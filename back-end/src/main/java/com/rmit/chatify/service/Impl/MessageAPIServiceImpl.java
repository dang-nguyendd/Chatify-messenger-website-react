package com.rmit.chatify.service.Impl;

import com.rmit.chatify.dtos.ConversationDTO;
import com.rmit.chatify.dtos.MessageDTO;
import com.rmit.chatify.dtos.UserDTO;
import com.rmit.chatify.model.Message;
import com.rmit.chatify.model.ResponseObject;
import com.rmit.chatify.service.APIService;
import com.rmit.chatify.service.serviceInterface.MessageService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageAPIServiceImpl extends APIService implements MessageService {

    @Override
    @Cacheable(value = "messages")
    public List<MessageDTO> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    @Cacheable(value = "message", key = "#id")
    public MessageDTO findById(Long id) {
        Optional<MessageDTO> foundMessage = messageRepository.findById(id);
        return foundMessage.orElse(null);
    }

    @Override
    @Cacheable(value = "messages", key = "#conversationId")
    public Page<MessageDTO> getAllMessagesByConversationId(Long conversationId, int page, int size) {
        Page<MessageDTO> foundMessage = messageRepository.getAllMessagesByConversationId(conversationId, PageRequest.of(page, size, Sort.by("timeStamp").descending()));
        if (foundMessage.isEmpty()) {
            return null;
        }
        return foundMessage;
    }

    public ConversationDTO insertMessage(Long id, Message message) {
        //Insert message into conversation set
        ConversationDTO conversation = conversationRepository.findById(id).get();
        if (!senderCheck(message.getSenderId(), conversation)) {
            return null;
        }
        UserDTO sender = userRepository.findById(message.getSenderId()).get();
        MessageDTO newMessage = MessageDTO.from(message);
        newMessage.setConversationDTO(conversation);
        newMessage.setSender(sender);
        messageRepository.save(newMessage);
        //Insert conversation into user set
        return conversationRepository.save(conversation);
    }

    @Override
    public Page<MessageDTO> getAllMessagesByConversationId(Long conversationId, Pageable pageable) {
        //Retrive from messagedto
        Page<MessageDTO> foundMessage = messageRepository.getAllMessagesByConversationId(conversationId, pageable);
        return foundMessage;
    }


    //Check if sender is in the participant list
    private boolean senderCheck(Long senderId, ConversationDTO conversation) {
        for (UserDTO user : conversation.getParticipants()) {
            if (user.getId() == senderId) {
                return true;
            }
        }
        return false;
    }
}