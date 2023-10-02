package com.rmit.chatify.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatNotification {
    private String id;
    private String senderId;
    private String senderName;
}