package com.enterprise.chat.authservice.dto.response;

public class LoginResponse {
    private String token;
    private String tokenType;
    private Long userId;
    private String userName;

    public LoginResponse() {
    }

    public LoginResponse(String token, String tokenType, Long userId, String userName) {
        this.token = token;
        this.tokenType = tokenType;
        this.userId = userId;
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}