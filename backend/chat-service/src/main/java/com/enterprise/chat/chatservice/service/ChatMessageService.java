package com.enterprise.chat.chatservice.service;

import com.enterprise.chat.chatservice.dto.ChatMessageResponse;
import com.enterprise.chat.chatservice.entity.ChatMessage;
import com.enterprise.chat.chatservice.entity.MessageType;
import java.util.List;

public interface ChatMessageService {
    ChatMessageResponse sendMessage(Long roomId, Long senderId, String content, MessageType messageType);
    List<ChatMessageResponse> getMessageByRoom(Long roomId);
    List<ChatMessageResponse> getMessageBySender(Long senderId);
    ChatMessageResponse getMessageById(Long messageId);
    void deleteMessage(Long messageId);
}
