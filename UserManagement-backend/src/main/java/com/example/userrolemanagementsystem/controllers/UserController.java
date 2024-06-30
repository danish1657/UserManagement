package com.example.userrolemanagementsystem.controllers;

import com.example.userrolemanagementsystem.dtos.UserDto;
import com.example.userrolemanagementsystem.exception.InvalidRequestException;
import com.example.userrolemanagementsystem.exception.ResourceNotFoundException;
import com.example.userrolemanagementsystem.mappers.UserMapper;
import com.example.userrolemanagementsystem.models.User;
import com.example.userrolemanagementsystem.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDto> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(UserMapper.toDto(user));
        }
        return userDTOs;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userCreateDto) {
        User user = UserMapper.toEntity(userCreateDto);
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(UserMapper.toDto(createdUser), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId,@Valid  @RequestBody UserDto userDto) {
        try {
            User existingUser = userService.getUserById(userId);
            if (existingUser.getVersion() != userDto.getVersion()) {
                throw new InvalidRequestException("Version mismatch: the provided version does not match the current version.");
            }

            User user = UserMapper.toEntity(userDto);
            user.setId(userId);
            User updatedUser = userService.updateUser(user);
            return new ResponseEntity<>(UserMapper.toDto(updatedUser), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException("User with ID " + userId + " not found.");
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException("User with ID " + userId + " not found.");
        }
    }
}
