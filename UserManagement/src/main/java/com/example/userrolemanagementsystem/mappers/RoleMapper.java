// RoleMapper.java
package com.example.userrolemanagementsystem.mappers;

import com.example.userrolemanagementsystem.dtos.RoleDto;
import com.example.userrolemanagementsystem.models.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public static RoleDto toDTO(Role role) {
        RoleDto dto = new RoleDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        dto.setVersion(role.getVersion());
        return dto;
    }

    public static Role toEntity(RoleDto dto) {
        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        role.setVersion(dto.getVersion());
        return role;
    }
}
