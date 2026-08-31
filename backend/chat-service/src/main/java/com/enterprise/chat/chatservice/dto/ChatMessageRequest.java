package com.enterprise.chat.chatservice.dto;

import com.enterprise.chat.chatservice.entity.MessageType;

public class ChatMessageRequest {

    private Long roomId;
    private Long senderId;
    private String content;
    private MessageType messageType;

    public  ChatMessageRequest(){}

    public ChatMessageRequest(Long roomId, Long senderId, String content, MessageType messageType) {
        this.roomId = roomId;
        this.senderId = senderId;
        this.content = content;
        this.messageType = messageType;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
