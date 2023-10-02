package com.rmit.chatify.service;

import com.rmit.chatify.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public abstract class APIService {
    @Autowired
    protected ConversationRepository conversationRepository;

    @Autowired
    protected RedisTemplate redisTemplate;

    @Autowired
    protected MessageRepository messageRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected FriendRepository friendRepository;

    @Autowired
    protected ProfileRepository profileRepository;

    @Autowired
    protected NotificationRepository notificationRepository;
}
