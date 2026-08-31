package com.enterprise.chat.chatservice.controller;

import com.enterprise.chat.chatservice.dto.ChatRoomParticipantResponse;
import com.enterprise.chat.chatservice.entity.ChatRoomParticipant;
import com.enterprise.chat.chatservice.service.ChatRoomParticipantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/chat/rooms")
public class ChatRoomParticipantController {
      private final ChatRoomParticipantService participantService;

      public ChatRoomParticipantController(ChatRoomParticipantService participantService){
          this.participantService=participantService;
      }
      @PostMapping("/{roomId}/participants/{userId}")
      public ResponseEntity<ChatRoomParticipantResponse> addParticipant(@PathVariable Long roomId,
                                                                @PathVariable Long userId){
          ChatRoomParticipantResponse participant=participantService.addParticipant(roomId,userId);
          return new ResponseEntity<>(participant, HttpStatus.CREATED);
      }
      @GetMapping("/{roomId}/participants")
      public ResponseEntity<List<ChatRoomParticipantResponse>> getParticipantsByRoom(
              @PathVariable Long roomId){
          return ResponseEntity.ok(participantService.getParticipantsByRoom(roomId));
      }
      @GetMapping("/participants/user/{userId}")
      public ResponseEntity<List<ChatRoomParticipantResponse>> getRoomsByUser(
              @PathVariable Long userId){
          return ResponseEntity.ok(participantService.getRoomsByUser(userId));

      }
      @DeleteMapping("/{roomId}/participants/{userId}")
      public ResponseEntity<Void> removeParticipant(
              @PathVariable Long roomId,
              @PathVariable Long userId){
          participantService.removeParticipant(roomId,userId);
          return ResponseEntity.noContent().build();
      }


}
