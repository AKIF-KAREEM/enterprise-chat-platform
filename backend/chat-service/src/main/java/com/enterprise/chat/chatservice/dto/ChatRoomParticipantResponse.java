package com.enterprise.chat.chatservice.dto;

import com.enterprise.chat.chatservice.entity.ChatRoom;

public class ChatRoomParticipantResponse {

    private Long id;
    private Long roomId;
    private String roomName;
    private Long userId;

    public ChatRoomParticipantResponse() {}

    public ChatRoomParticipantResponse(Long id, Long roomId, String roomName, Long userId) {
        this.id = id;
        this.roomId = roomId;
        this.roomName = roomName;
        this.userId = userId;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
