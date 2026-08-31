package com.enterprise.chat.chatservice.service;
import com.enterprise.chat.chatservice.dto.ChatRoomParticipantResponse;


import java.util.List;

public interface ChatRoomParticipantService {

   ChatRoomParticipantResponse addParticipant(Long roomId, Long userId);
   List<ChatRoomParticipantResponse> getParticipantsByRoom(Long roomId);
   List<ChatRoomParticipantResponse> getRoomsByUser(Long userId);
   void removeParticipant(Long roomId, Long userId);
}
