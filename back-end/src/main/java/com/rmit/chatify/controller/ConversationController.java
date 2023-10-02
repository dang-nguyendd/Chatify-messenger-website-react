package com.rmit.chatify.controller;

import com.rmit.chatify.dtos.ConversationDTO;
import com.rmit.chatify.dtos.MessageDTO;
import com.rmit.chatify.model.Conversation;
import com.rmit.chatify.model.Message;
import com.rmit.chatify.model.ResponseObject;
import com.rmit.chatify.service.serviceInterface.ConversationService;
import com.rmit.chatify.service.serviceInterface.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/conversations")
@CrossOrigin(origins = "*")
public class ConversationController {
    //DI = Dependency Injection
    @Autowired
    private ConversationService service;

    @Autowired
    private SearchService searchService;

    @GetMapping("")
        //this request is: http://localhost:5432/Conversations
    List<ConversationDTO> getAllConvos() {
        return service.getAllConvos();
    }

    @GetMapping("/{conversationId}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long conversationId) {
        return service.findById(conversationId);
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertConvo(@RequestBody Conversation newConversation) {
        return service.insertConvo(newConversation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateConvo(@RequestBody Conversation newConversation, @PathVariable Long id) {
        return service.updateConvo(newConversation, id);
    }

//    Find conversation by name
    @GetMapping("/search")
    public ResponseEntity<ResponseObject> findConversationByName(@RequestParam String conversationName, @RequestParam int page, @RequestParam int size) {
        try {
            List<ConversationDTO> res = searchService.getConversationByName( conversationName, page, size);
            return res != null ?
                    ResponseEntity.ok(new ResponseObject("200", "Conversation found", res)) :
                    ResponseEntity.ok(new ResponseObject("404", "Conversation not found", null));

        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseObject("500", "Conversation module errror", null));
        }
    }

    //Get conversation by participant id
    @GetMapping("/getConversationByParticipantId/{participantId}")
    public ResponseEntity<ResponseObject> getConversationByParticipantIdRequest(@PathVariable Long participantId, @RequestParam int page, @RequestParam int size) {
        return service.getConverSationByParticipantId(participantId, page, size);
    }

    @GetMapping("/search_message/{conversation_id}")
    ResponseEntity<ResponseObject> searchMessageByConversationID(@PathVariable Long conversation_id, @RequestParam String keyword, @RequestParam int page, @RequestParam int size) {
        try {
            List<MessageDTO> res = searchService.getMessageInConversation(conversation_id, keyword, page, size);
            return res != null ?
                    ResponseEntity.ok(new ResponseObject("200", "Message found", res)) :
                    ResponseEntity.ok(new ResponseObject("404", "Message not found", null));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseObject("500", "Message module error", null));
        }
    }
}