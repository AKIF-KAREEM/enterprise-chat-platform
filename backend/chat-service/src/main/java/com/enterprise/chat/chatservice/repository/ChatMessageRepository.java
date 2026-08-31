package com.enterprise.chat.chatservice.repository;

import com.enterprise.chat.chatservice.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatRoomIdOrderByCreatedAtAsc(Long chatRoomId);
    List<ChatMessage> findBySenderIdOrderByCreatedAtAsc(Long senderId);

}
