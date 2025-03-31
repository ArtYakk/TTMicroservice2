package com.artemyakkonen.spring.boot.ttmicroservice2.entity;

import com.artemyakkonen.spring.boot.ttmicroservice2.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "messages")
public class Message {//id, user_id, body, time
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "body")
    private String body;

    @Column(name = "time")
    private LocalDateTime time;
}
