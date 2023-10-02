package com.rmit.chatify.controller;

import com.rmit.chatify.dtos.ConversationDTO;
import com.rmit.chatify.dtos.MessageDTO;
import com.rmit.chatify.model.Message;
import com.rmit.chatify.model.ResponseObject;
import com.rmit.chatify.service.serviceInterface.MessageService;
import com.rmit.chatify.service.serviceInterface.SearchService;
import com.rmit.chatify.socketService.Impl.MessageSocketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/messages")
@CrossOrigin(origins = "*")
public class MessageController {
    //DI = Dependency Injection
    @Autowired
    private MessageService service;

    @Autowired
    private MessageSocketServiceImpl messageSocketService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @Autowired
    private SearchService searchService;

    @GetMapping("/joinRoom/{conversationId}")
    @SendTo("/topic/messages/{conversationId}")
    public List<MessageDTO> joinRoom(@PathVariable Long conversationId) {
        return service.getAllMessagesByConversationId(conversationId, Pageable.unpaged()).getContent();
    }

    @PostMapping("/chat")
    public void processMessage(@RequestBody Message chatMessage) {
        //Save chat message
        System.out.println("Message content");
        System.out.println(chatMessage.getConversationId());
        service.insertMessage(chatMessage.getConversationId(), chatMessage);
        List<MessageDTO> messages = service.getAllMessagesByConversationId(chatMessage.getConversationId(), Pageable.unpaged()).getContent();
        messagingTemplate.convertAndSend("/topic/messages/" + chatMessage.getConversationId(), messages);
        //Send push notification
    }

    @GetMapping("")
        //this request is: http://localhost:5432/Messages
    List<MessageDTO> getAllMessages() {
        return service.getAllMessages();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        try {
            MessageDTO res = service.findById(id);
            if (res != null) {
                return ResponseEntity.ok(new ResponseObject("200", "Message found", res));
            }
            return ResponseEntity.ok(new ResponseObject("404", "Message not found", null));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseObject("500", "Message Service error", null));
        }
    }

    @PutMapping("/insertMessage/{id}")
    ResponseEntity<ResponseObject> insertMessage(@RequestBody Message newMessage, @PathVariable Long id) {
        try {
            ConversationDTO res = service.insertMessage(id, newMessage);
            if (res != null) {
                return ResponseEntity.ok(new ResponseObject("200", "Message inserted", res));
            } else {
                return ResponseEntity.ok(new ResponseObject("404", "Message not inserted", null));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseObject("500", "Message Service error", null));
        }
    }
}
