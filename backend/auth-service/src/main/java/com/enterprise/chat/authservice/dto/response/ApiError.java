package com.enterprise.chat.authservice.dto.response;

import java.time.LocalDateTime;

public class ApiError {
    private int status;
    private String message;
    private LocalDateTime timestamp;

    public ApiError(){
        this.timestamp = LocalDateTime.now();
    }
    public ApiError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {

    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {

    }

}
