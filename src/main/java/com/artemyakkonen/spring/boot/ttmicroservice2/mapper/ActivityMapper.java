package com.artemyakkonen.spring.boot.ttmicroservice2.mapper;

import com.artemyakkonen.spring.boot.ttmicroservice2.dto.ActivityDTO;
import com.artemyakkonen.spring.boot.ttmicroservice2.entity.Activity;

import java.util.List;
import java.util.stream.Collectors;

public class ActivityMapper {
    public static ActivityDTO toActivityDTO(Activity activity) {
        if(activity == null) {return null;}
        return ActivityDTO.builder()
                .id(activity.getId())
                .time(activity.getTime())
                .type(activity.getType())
                .build();
    }

    public static Activity toActivity(ActivityDTO activityDTO) {
        if(activityDTO == null) {return null;}
        return Activity.builder()
                .id(activityDTO.getId())
                .time(activityDTO.getTime())
                .type(activityDTO.getType())
                .build();
    }

    public static List<ActivityDTO> toActivityDTOList(List<Activity> activityList) {
        return activityList.stream()
                .map(ActivityMapper::toActivityDTO)
                .collect(Collectors.toList());
    }

    public static List<Activity> toActivityList(List<ActivityDTO> activityDTOList) {
        return activityDTOList.stream()
                .map(ActivityMapper::toActivity)
                .collect(Collectors.toList());
    }
}
