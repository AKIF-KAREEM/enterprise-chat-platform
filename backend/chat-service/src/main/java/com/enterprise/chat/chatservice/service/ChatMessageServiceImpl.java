package com.enterprise.chat.chatservice.service;

import com.enterprise.chat.chatservice.dto.ChatMessageRequest;
import com.enterprise.chat.chatservice.dto.ChatMessageResponse;
import com.enterprise.chat.chatservice.entity.ChatMessage;
import com.enterprise.chat.chatservice.entity.ChatRoom;
import com.enterprise.chat.chatservice.entity.MessageType;
import com.enterprise.chat.chatservice.repository.ChatMessageRepository;
import com.enterprise.chat.chatservice.repository.ChatRoomParticipantRepository;
import com.enterprise.chat.chatservice.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ChatMessageServiceImpl implements ChatMessageService{

   private final ChatMessageRepository chatMessageRepository;
   private final ChatRoomRepository chatRoomRepository;
   private final ChatRoomParticipantRepository  chatRoomParticipantRepository;

   public ChatMessageServiceImpl(ChatMessageRepository chatMessageRepository,
                                 ChatRoomRepository chatRoomRepository,
                                 ChatRoomParticipantRepository chatRoomParticipantRepository)
   {
       this.chatMessageRepository = chatMessageRepository;
       this.chatRoomRepository = chatRoomRepository;
       this.chatRoomParticipantRepository = chatRoomParticipantRepository;
   }

    @Override
    @Transactional
    public ChatMessageResponse sendMessage(Long roomId, Long senderId, String content,MessageType messageType) {

       ChatRoom chatRoom= chatRoomRepository.findById(roomId)
               .orElseThrow(()->new RuntimeException(
                       "Chat room not found with Id: "+roomId));
       if(!chatRoomParticipantRepository
               .existsByChatRoomIdAndUserId(roomId, senderId)){
           throw new RuntimeException("User "
                   +senderId+" is not a participant of room "+roomId);
       }

       ChatMessage message=new ChatMessage();
       message.setChatRoom(chatRoom);
       message.setSenderId(senderId);
       message.setContent(content);
       message.setMessageType(messageType);
       message.setCreatedAt(java.time.LocalDateTime.now());
       ChatMessage savedMessage=chatMessageRepository.save(message);

        return mapToResponse(savedMessage);
    }

    @Override
    public List<ChatMessageResponse> getMessageByRoom(Long roomId) {

       if(!chatRoomRepository.existsById(roomId)){
           throw new RuntimeException("Chat room not found with Id: "+roomId);
       }

       return chatMessageRepository.findByChatRoomIdOrderByCreatedAtAsc(roomId)
               .stream()
               .map(this::mapToResponse)
               .toList();
    }

    @Override
    public List<ChatMessageResponse> getMessageBySender(Long senderId) {
        return chatMessageRepository.findBySenderIdOrderByCreatedAtAsc(senderId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ChatMessageResponse getMessageById(Long messageId) {
         ChatMessage message= chatMessageRepository.findById(messageId).orElseThrow(
                ()->new RuntimeException("Message not found with Id: "+messageId)
        );
         return mapToResponse(message);
    }

    @Override
    @Transactional
    public void deleteMessage(Long messageId) {
      if(!chatMessageRepository.existsById(messageId)){
          throw new RuntimeException("Message not found with Id: "+messageId);
      }
      chatMessageRepository.deleteById(messageId);

    }
    private ChatMessageResponse mapToResponse(ChatMessage message) {
       return new ChatMessageResponse(
               message.getMessageId(),
               message.getChatRoom().getId(),
               message.getSenderId(),
               message.getContent(),
               message.getMessageType(),
               message.getCreatedAt()
       );
    }
}
