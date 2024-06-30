package com.example.userrolemanagementsystem.mappers;

import com.example.userrolemanagementsystem.dtos.UserDto;
import com.example.userrolemanagementsystem.models.User;

public class UserMapper {
    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setVersion(user.getVersion());
        return dto;
    }

    public static User toEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setVersion(dto.getVersion());
        return user;
    }
}