package com.rmit.chatify.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(name = "ResponseObjectSchema", description = "Response Object Schema")
public class ResponseObject {
    @Schema(description = "Response status")
    private String status;
    @Schema(description = "Response message")
    private String message;
    @Schema(description = "Response data")
    private Object data;

}
