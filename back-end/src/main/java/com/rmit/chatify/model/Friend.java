package com.rmit.chatify.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "FriendSchema", description = "Friend Schema")
public class Friend {
    @Schema(description = "Friend ID 1")
    private Long id1;

    @Schema(description = "Friend ID 2")
    private Long id2;

    @Schema(description = "Friend isBlocked")
    private Boolean isBlocked = false;
}
