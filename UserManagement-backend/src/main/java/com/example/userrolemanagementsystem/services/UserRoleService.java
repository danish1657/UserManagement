package com.example.userrolemanagementsystem.services;

import com.example.userrolemanagementsystem.exception.ResourceNotFoundException;
import com.example.userrolemanagementsystem.models.UserRole;
import com.example.userrolemanagementsystem.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Transactional(readOnly = true)
    public List<UserRole> getUserRoles(Long userId, Long unitId, LocalDateTime timestamp) {
        return userRoleRepository.findByUserIdAndUnitIdAndValidFromBeforeAndValidToAfter(
                userId, unitId, timestamp, timestamp);
    }

    public UserRole getUserRoleById(Long userRoleId) {
        return userRoleRepository.findById(userRoleId).orElseThrow(() -> new ResourceNotFoundException("UserRole not found with ID: " + userRoleId));
    }

    @Transactional
    public UserRole createUserRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Transactional
    public UserRole updateUserRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Transactional
    public void deleteUserRole(Long userRoleId) {
        userRoleRepository.deleteById(userRoleId);
    }

    public List<UserRole> getValidUserRoles(Long userId, Long unitId, LocalDateTime timestamp) {
        return userRoleRepository.findValidUserRoles(userId, unitId, timestamp);
    }
}
