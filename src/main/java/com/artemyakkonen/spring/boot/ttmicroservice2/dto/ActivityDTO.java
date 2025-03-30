package com.artemyakkonen.spring.boot.ttmicroservice2.dto;


import com.artemyakkonen.spring.boot.ttmicroservice2.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityDTO {
    private Long id;
    private LocalDateTime time;
    private String type;
}
