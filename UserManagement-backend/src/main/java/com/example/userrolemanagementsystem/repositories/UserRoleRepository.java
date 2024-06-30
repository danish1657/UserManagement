package com.example.userrolemanagementsystem.repositories;

import com.example.userrolemanagementsystem.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findByUserIdAndUnitIdAndValidFromBeforeAndValidToAfter(
            Long userId, Long unitId, LocalDateTime validTo, LocalDateTime validFrom);

    @Query("SELECT ur FROM UserRole ur " +
            "WHERE ur.user.id = :userId " +
            "AND ur.unit.id = :unitId " +
            "AND ur.validFrom <= :timestamp " +
            "AND (ur.validTo IS NULL OR ur.validTo >= :timestamp)")
    List<UserRole> findValidUserRoles(@Param("userId") Long userId,
                                      @Param("unitId") Long unitId,
                                      @Param("timestamp") LocalDateTime timestamp);
}
