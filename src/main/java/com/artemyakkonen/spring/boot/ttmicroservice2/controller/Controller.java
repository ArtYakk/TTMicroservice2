package com.artemyakkonen.spring.boot.ttmicroservice2.controller;

import com.artemyakkonen.spring.boot.ttmicroservice2.dto.MessageResponse;
import com.artemyakkonen.spring.boot.ttmicroservice2.dto.UserResponse;
import com.artemyakkonen.spring.boot.ttmicroservice2.entity.User;
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
    public ResponseEntity<List<UserResponse>> getAllUsers() {
       return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/active")
    public ResponseEntity<List<UserResponse>> getActiveUsers() {
        return ResponseEntity.ok(userService.getActiveUsers());
    }

    @GetMapping("/messages/{requestedId}")
    public ResponseEntity<List<MessageResponse>> getMessagesByUserId(@PathVariable Long requestedId) {
        User user = userService.getUserById(requestedId);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(messageService.getMessagesByUserId(requestedId));
    }

    @DeleteMapping("/users/{requestedId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long requestedId) {
        User user = userService.getUserById(requestedId);
        if(user != null) {
            userService.deleteUserById(requestedId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/messages/{requestedId}")
    public ResponseEntity<Void> deleteMessageById(@PathVariable Long requestedId) {
        MessageResponse messageResponse = messageService.getMessageById(requestedId);
        if(messageResponse != null) {
            messageService.deleteMessageById(requestedId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }




}
