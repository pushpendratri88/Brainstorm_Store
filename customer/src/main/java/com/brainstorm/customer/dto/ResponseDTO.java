package com.brainstorm.customer.dto;

public class ResponseDTO {

    private String statusCode;
    private String statusMsg;

    public ResponseDTO(String status201, String message201) {
       this.statusCode= status201;
       this.statusMsg = message201;

    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }
}
