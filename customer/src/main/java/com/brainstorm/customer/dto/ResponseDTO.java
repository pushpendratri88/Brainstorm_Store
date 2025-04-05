package com.brainstorm.customer.dto;
import lombok.Data;

@Data
public class ResponseDTO {

    private String statusCode;
    private String statusMsg;

    public ResponseDTO(String status201, String message201) {
       this.statusCode= status201;
       this.statusMsg = message201;

    }

}
