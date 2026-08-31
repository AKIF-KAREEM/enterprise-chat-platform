package com.enterprise.chat.chatservice.service;

import com.enterprise.chat.chatservice.entity.ChatRoom;
import com.enterprise.chat.chatservice.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    public ChatRoomServiceImpl(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository=chatRoomRepository;
    }

    @Override
    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    @Override
    public List<ChatRoom> getAllRooms() {
        return chatRoomRepository.findAll();
    }

    @Override
    public ChatRoom getRoomById(Long id) {
        return chatRoomRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Chat room not found with Id: "+ id));
    }

    @Override
    public ChatRoom updateRoom(Long id, ChatRoom chatRoom) {

        ChatRoom existingRoom=getRoomById(id);

        existingRoom.setRoomName(chatRoom.getRoomName());
        existingRoom.setRoomType(chatRoom.getRoomType());

        return chatRoomRepository.save(existingRoom);
    }

    @Override
    public void deleteRoom(Long id) {
     ChatRoom existingRoom=getRoomById(id);
     chatRoomRepository.delete(existingRoom);
    }
}
