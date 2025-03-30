package com.artemyakkonen.spring.boot.ttmicroservice2.dto;

import com.artemyakkonen.spring.boot.ttmicroservice2.entity.Activity;
import com.artemyakkonen.spring.boot.ttmicroservice2.entity.Message;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String uuid;
    private String role;
    @Builder.Default
    private List<ActivityDTO> activities = new ArrayList<>();
    @Builder.Default
    private List<MessageDTO> messages = new ArrayList<>();
}
