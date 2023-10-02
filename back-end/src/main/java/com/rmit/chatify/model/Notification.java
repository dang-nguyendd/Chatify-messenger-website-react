package com.rmit.chatify.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;

import java.util.ArrayList;
import java.util.List;

@Schema
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notification {
    @Schema
    private long id;

    @Schema
    private String content;

    @Schema
    List<Long> userId = new ArrayList<>();
}
