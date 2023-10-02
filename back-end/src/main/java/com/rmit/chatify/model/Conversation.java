package com.rmit.chatify.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Schema(name = "ConversationSchema", description = "Conversation Schema")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Conversation {
    @Schema(description = "Conversation Photo")
    private String conversationPhoto;
    @Schema(description = "Conversation Name")
    private String conversationName;

    @Schema(description = "Conversation Participant")
    private Set<Long> participants;

}