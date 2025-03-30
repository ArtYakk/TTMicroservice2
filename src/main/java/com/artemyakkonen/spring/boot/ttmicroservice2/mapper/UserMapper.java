package com.artemyakkonen.spring.boot.ttmicroservice2.mapper;

import com.artemyakkonen.spring.boot.ttmicroservice2.dto.UserDTO;
import com.artemyakkonen.spring.boot.ttmicroservice2.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO toUserDTO(User user) {
        if(user == null) {return null;}
        return UserDTO.builder()
                .id(user.getId())
                .uuid(user.getUuid())
                .role(user.getRole())
                .activities(ActivityMapper.toActivityDTOList(user.getActivities()))
                .messages(MessageMapper.toMessageDTOList(user.getMessages()))
                .build();
    }

    public static User toUser(UserDTO userDTO) {
        if(userDTO == null) {return null;}
        return User.builder()
                .id(userDTO.getId())
                .uuid(userDTO.getUuid())
                .role(userDTO.getRole())
                .activities(ActivityMapper.toActivityList(userDTO.getActivities()))
                .messages(MessageMapper.toMessageList(userDTO.getMessages()))
                .build();
    }

    public static List<UserDTO> toUserDTOList(List<User> userList) {
        return userList.stream().map(UserMapper::toUserDTO).collect(Collectors.toList());
    }

    public static List<User> toUserList(List<UserDTO> userDTOList) {
        return userDTOList.stream().map(UserMapper::toUser).collect(Collectors.toList());
    }
}