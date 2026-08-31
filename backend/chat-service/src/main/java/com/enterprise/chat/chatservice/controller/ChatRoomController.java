package com.enterprise.chat.chatservice.controller;

import com.enterprise.chat.chatservice.entity.ChatRoom;
import com.enterprise.chat.chatservice.service.ChatRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat/rooms")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    public ChatRoomController(ChatRoomService chatRoomService){
        this.chatRoomService=chatRoomService;
    }
    @PostMapping
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody ChatRoom chatRoom){
        ChatRoom createdRoom=chatRoomService.createChatRoom(chatRoom);
        return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ChatRoom>> getAllRooms(){
        return ResponseEntity.ok(chatRoomService.getAllRooms());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ChatRoom> getRoomById(@PathVariable Long id){
        return ResponseEntity.ok(chatRoomService.getRoomById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ChatRoom> updateRoom(@PathVariable Long id, @RequestBody ChatRoom chatRoom){
        return ResponseEntity.ok(chatRoomService.updateRoom(id, chatRoom));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id){
        chatRoomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }

}
