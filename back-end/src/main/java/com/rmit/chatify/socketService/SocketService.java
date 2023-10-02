package com.rmit.chatify.socketService;

import com.rmit.chatify.model.Message;
import com.rmit.chatify.repository.MessageRepository;
import com.rmit.chatify.service.serviceInterface.FriendService;
import com.rmit.chatify.service.serviceInterface.MessageService;
import com.rmit.chatify.service.serviceInterface.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class SocketService {
    @Autowired
    protected FriendService friendService;

    @Autowired
    protected MessageService messageService;


    @Autowired
    protected MessageRepository messageRepository;
}
