package com.enterprise.chat.chatservice.service;

import com.enterprise.chat.chatservice.dto.ChatRoomParticipantResponse;
import com.enterprise.chat.chatservice.entity.ChatRoom;
import com.enterprise.chat.chatservice.entity.ChatRoomParticipant;
import com.enterprise.chat.chatservice.repository.ChatRoomParticipantRepository;
import com.enterprise.chat.chatservice.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChatRoomParticipantServiceImpl implements ChatRoomParticipantService{

    private final ChatRoomParticipantRepository participantRepository;
    private final ChatRoomRepository chatRoomRepository;

      public ChatRoomParticipantServiceImpl(ChatRoomParticipantRepository participantRepository, ChatRoomRepository chatRoomRepository) {
          this.participantRepository=participantRepository;
          this.chatRoomRepository=chatRoomRepository;
      }

      public ChatRoomParticipantResponse mapToResponse(ChatRoomParticipant participant) {
          return new ChatRoomParticipantResponse(
                  participant.getId(),
                  participant.getChatRoom().getId(),
                  participant.getChatRoom().getRoomName(),
                  participant.getUserId()
          );
      }

    @Override
    public ChatRoomParticipantResponse addParticipant(Long roomId, Long userId) {
       ChatRoom chatRoom= chatRoomRepository.findById(roomId)
               .orElseThrow(()->new RuntimeException("Chat room not found with Id: "+roomId));

       if(participantRepository.existsByChatRoomIdAndUserId(roomId,userId)){
           throw new RuntimeException("User "+userId+" is already a participant of room "+roomId);
       }
        ChatRoomParticipant participant=new ChatRoomParticipant(chatRoom,userId);
        ChatRoomParticipant savedParticipant=participantRepository.save(participant);
        return mapToResponse(savedParticipant);
    }

    @Override
    public List<ChatRoomParticipantResponse> getParticipantsByRoom(Long roomId) {
        if(!chatRoomRepository.existsById(roomId)){
            throw new RuntimeException("Room not found with Id: "+roomId);
        }

          return participantRepository.findByChatRoomId(roomId)
                  .stream().map(this::mapToResponse).toList();
    }

    @Override
    public List<ChatRoomParticipantResponse> getRoomsByUser(Long userId) {
        return participantRepository.findByUserId(userId)
                .stream().map(this::mapToResponse).toList();
    }

    @Override
    @Transactional

    public void removeParticipant(Long roomId, Long userId) {

          if(!participantRepository.existsByChatRoomIdAndUserId(roomId,userId)){
              throw new RuntimeException("User "+userId+" is not a participant of room "+roomId);
        }
          participantRepository.deleteByChatRoomIdAndUserId(roomId, userId);

    }
}
