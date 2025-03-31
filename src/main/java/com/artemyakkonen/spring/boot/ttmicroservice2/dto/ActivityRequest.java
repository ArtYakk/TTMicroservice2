package com.artemyakkonen.spring.boot.ttmicroservice2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequest {
    private Long user_id;
    private LocalDateTime time;
    private String type;
}