package com.artemyakkonen.spring.boot.ttmicroservice2.dto;

import com.artemyakkonen.spring.boot.ttmicroservice2.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDTO{
    private Long id;
    private String body;
    private LocalDateTime time;
}
