package com.artemyakkonen.spring.boot.ttmicroservice2.controller;

import com.artemyakkonen.spring.boot.ttmicroservice2.dto.MessageDTO;
import com.artemyakkonen.spring.boot.ttmicroservice2.dto.UserDTO;
import com.artemyakkonen.spring.boot.ttmicroservice2.service.MessageService;
import com.artemyakkonen.spring.boot.ttmicroservice2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    UserService userService;
    MessageService messageService;

    @Autowired
    public Controller(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
       return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/active")
    public ResponseEntity<List<UserDTO>> getActiveUsers() {
        return ResponseEntity.ok(userService.getActiveUsers());
    }

    @GetMapping("/messages/{requestedId}")
    public ResponseEntity<List<MessageDTO>> getMessagesByUserId(@PathVariable Long requestedId) {
        return ResponseEntity.ok(messageService.getMessagesByUserId(requestedId));
    }

    @DeleteMapping("/users/{requestedId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long requestedId) {
        UserDTO userDTO = userService.getUserById(requestedId);
        if(userDTO != null) {
            userService.deleteUserById(requestedId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/messages/{requestedId}")
    public ResponseEntity<Void> deleteMessageById(@PathVariable Long requestedId) {
        MessageDTO messageDTO = messageService.getMessageById(requestedId);
        if(messageDTO != null) {
            messageService.deleteMessageById(requestedId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }




}
