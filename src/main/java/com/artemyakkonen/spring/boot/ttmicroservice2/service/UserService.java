package com.artemyakkonen.spring.boot.ttmicroservice2.service;

import com.artemyakkonen.spring.boot.ttmicroservice2.dto.ActivityDTO;
import com.artemyakkonen.spring.boot.ttmicroservice2.dto.MessageDTO;
import com.artemyakkonen.spring.boot.ttmicroservice2.dto.UserDTO;
import com.artemyakkonen.spring.boot.ttmicroservice2.entity.User;
import com.artemyakkonen.spring.boot.ttmicroservice2.mapper.ActivityMapper;
import com.artemyakkonen.spring.boot.ttmicroservice2.mapper.MessageMapper;
import com.artemyakkonen.spring.boot.ttmicroservice2.mapper.UserMapper;
import com.artemyakkonen.spring.boot.ttmicroservice2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers(){
        return UserMapper.toUserDTOList(userRepository.findAll());
    }

    public List<UserDTO> getActiveUsers(){
        return UserMapper.toUserDTOList(userRepository.findByActivitiesIsNotEmpty());
    }

    public void deleteUserById(Long id){
            userRepository.deleteById(id);
    }

    public UserDTO getUserById(Long id){
        User user =  userRepository.findById(id).orElse(null);
        if(user != null) {
            return UserMapper.toUserDTO(user);
        }
        return null;
    }

    @Transactional
    public UserDTO getUserByUuid(String uuid){
      User user =  userRepository.findByUuid(uuid).orElse(null);
      if(user != null) {
          return UserMapper.toUserDTO(user);
      }
      return null;
    }


    @Transactional
    public URI addUser(User user){
       User savedUser = userRepository.save(user);
        return UriComponentsBuilder.fromPath("/users/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
    }


}
