package com.enterprise.chat.chatservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name="chat_room_participants")
public class ChatRoomParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id", nullable=false)
    private ChatRoom chatRoom;
    @Column(name="user_id", nullable=false)
    private Long userId;

    public ChatRoomParticipant() {}
    public ChatRoomParticipant(ChatRoom chatRoom, Long userId) {
        this.chatRoom=chatRoom;
        this.userId=userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
