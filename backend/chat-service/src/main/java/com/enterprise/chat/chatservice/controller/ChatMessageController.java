package com.enterprise.chat.chatservice.controller;

import com.enterprise.chat.chatservice.dto.ChatMessageRequest;
import com.enterprise.chat.chatservice.dto.ChatMessageResponse;
import com.enterprise.chat.chatservice.entity.ChatMessage;
import com.enterprise.chat.chatservice.service.ChatMessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat/messages")
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    public ChatMessageController(ChatMessageService chatMessageService){
        this.chatMessageService=chatMessageService;
    }
    @PostMapping
    public ResponseEntity<ChatMessageResponse> sendMessage(
            @RequestBody ChatMessageRequest request){
        ChatMessageResponse response=chatMessageService.sendMessage(
                request.getRoomId(),
                request.getSenderId(),
                request.getContent(),
                request.getMessageType()
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<ChatMessageResponse>> getMessageByRoom(
            @PathVariable Long roomId
    ){
        List<ChatMessageResponse> responses=chatMessageService
                .getMessageByRoom(roomId);
        return ResponseEntity.ok(responses);
    }
    @GetMapping("/sender/{senderId}")
    public ResponseEntity<List<ChatMessageResponse>> getMessageBySender(
            @PathVariable Long senderId){

        List<ChatMessageResponse> responses=chatMessageService.
                getMessageBySender(senderId);

        return ResponseEntity.ok(responses);
    }
    @GetMapping("/{messageId}")
    public ResponseEntity<ChatMessageResponse> getMessageById(
            @PathVariable Long messageId){
        ChatMessageResponse responses=chatMessageService
                .getMessageById(messageId);

        return ResponseEntity.ok(responses);
    }
    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessage(
            @PathVariable Long messageId){
        chatMessageService.deleteMessage(messageId);
        return ResponseEntity.noContent().build();
    }
}
