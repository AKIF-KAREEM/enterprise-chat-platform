package com.enterprise.chat.chatservice.repository;

import com.enterprise.chat.chatservice.entity.ChatRoomParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomParticipantRepository extends JpaRepository<ChatRoomParticipant, Long> {

    List<ChatRoomParticipant> findByChatRoomId(Long roomId);
    List<ChatRoomParticipant> findByUserId(Long userId);
    boolean existsByChatRoomIdAndUserId(Long roomId, Long userId);
    void deleteByChatRoomIdAndUserId(Long roomId, Long userId);
}
