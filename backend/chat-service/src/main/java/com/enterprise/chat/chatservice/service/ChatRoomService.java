package com.enterprise.chat.chatservice.service;

import com.enterprise.chat.chatservice.entity.ChatRoom;

import java.util.List;

public interface ChatRoomService {

    ChatRoom createChatRoom(ChatRoom chatRoom);
    List<ChatRoom> getAllRooms();
    ChatRoom getRoomById(Long id);
    ChatRoom updateRoom(Long id, ChatRoom chatRoom);
    void deleteRoom(Long id);
}
