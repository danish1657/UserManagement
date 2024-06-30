package com.example.userrolemanagementsystem.controllers;

import com.example.userrolemanagementsystem.exception.InvalidRequestException;
import com.example.userrolemanagementsystem.models.UserRole;
import com.example.userrolemanagementsystem.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/userroles")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/{userId}/{unitId}/{timestamp}")
    public List<UserRole> getUserRoles(
            @PathVariable Long userId, @PathVariable Long unitId, @PathVariable LocalDateTime timestamp) {
        return userRoleService.getUserRoles(userId, unitId, timestamp);
    }

    @PostMapping
    public ResponseEntity<UserRole> createUserRole(@RequestBody UserRole userRole) {
        UserRole createdUserRole = userRoleService.createUserRole(userRole);
        return new ResponseEntity<>(createdUserRole, HttpStatus.CREATED);
    }

    @PutMapping("/{userRoleId}")
    public ResponseEntity<UserRole> updateUserRole(
            @PathVariable Long userRoleId, @RequestBody UserRole userRole) {

        UserRole existingUserRole = userRoleService.getUserRoleById(userRoleId);
        if (existingUserRole.getVersion()!=userRole.getVersion()) {
            throw new InvalidRequestException("Version mismatch: the provided version does not match the current version.");
        }


        userRole.setId(userRoleId);
        UserRole updatedUserRole = userRoleService.updateUserRole(userRole);
        return new ResponseEntity<>(updatedUserRole, HttpStatus.OK);
    }

    @DeleteMapping("/{userRoleId}")
    public ResponseEntity<Void> deleteUserRole(@PathVariable Long userRoleId) {
        userRoleService.deleteUserRole(userRoleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/valid")
    public ResponseEntity<List<UserRole>> getValidUserRoles(
            @RequestParam Long userId,
            @RequestParam Long unitId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timestamp) {
        List<UserRole> validUserRoles = userRoleService.getValidUserRoles(userId, unitId, timestamp);
        return ResponseEntity.ok().body(validUserRoles);
    }
}