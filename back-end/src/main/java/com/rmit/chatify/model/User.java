package com.rmit.chatify.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "UserSchema", description = "User Schema")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Schema(description = "User Name")
    private String username;
    @Schema(description = "User Email")
    private String email;
    @Schema(description = "User Password")
    private String password;
    @Schema(description = "Avatar URL")
    private String avatar;
    @Schema(description = "status")
    private String status;
    @Schema(description = "isOnline")
    private Boolean  isOnline;
}