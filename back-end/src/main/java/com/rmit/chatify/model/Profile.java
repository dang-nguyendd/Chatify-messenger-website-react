package com.rmit.chatify.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(name = "ProfileSchema", description = "Profile Schema")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Profile {
    @Schema(description = "images list")
    private List<String> images;

    @Schema(description = "User bio")
    private String bio;
}
