package com.rmit.chatify.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(name = "MessageSchema", description = "Message Schema")
public class Message {
    @Schema(description = "Message content")
    private String content;

    @Schema(description = "Message seen status")
    private Boolean isSeen;

    @Schema(description = "Message media url")
    private String mediaUrl;

    @Schema(description = "Message sender")
    private Long senderId;

    @Schema(description = "ConversationId")
    private Long conversationId;
}