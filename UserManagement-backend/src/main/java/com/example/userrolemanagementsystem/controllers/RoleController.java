package com.example.userrolemanagementsystem.controllers;
import com.example.userrolemanagementsystem.dtos.RoleDto;
import com.example.userrolemanagementsystem.exception.InvalidRequestException;
import com.example.userrolemanagementsystem.mappers.RoleMapper;
import com.example.userrolemanagementsystem.models.Role;
import com.example.userrolemanagementsystem.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        List<RoleDto> roleDTOs = new ArrayList<>();
        for (Role role : roles) {
            roleDTOs.add(RoleMapper.toDTO(role));
        }
        return roleDTOs;
    }

    @PostMapping
    public ResponseEntity<RoleDto> createRole(@Valid @RequestBody RoleDto roleDTO) {
        Role role = RoleMapper.toEntity(roleDTO);
        Role createdRole = roleService.createRole(role);
        RoleDto createdRoleDTO = RoleMapper.toDTO(createdRole);
        return new ResponseEntity<>(createdRoleDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<RoleDto> updateRole(@Valid @PathVariable Long roleId, @RequestBody RoleDto roleDTO) {
        Role existingRole = roleService.getRoleById(roleId);
        if (existingRole.getVersion() != roleDTO.getVersion()) {
            throw new InvalidRequestException("Version mismatch: the provided version does not match the current version.");
        }
        roleDTO.setId(roleId);
        Role roleToUpdate = RoleMapper.toEntity(roleDTO);
        Role updatedRole = roleService.updateRole(roleToUpdate);
        RoleDto updatedRoleDTO = RoleMapper.toDTO(updatedRole);
        return new ResponseEntity<>(updatedRoleDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long roleId) {
        roleService.deleteRole(roleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}