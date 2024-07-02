package com.example.userrolemanagementsystem.mappers;

import com.example.userrolemanagementsystem.dtos.UnitDto;
import com.example.userrolemanagementsystem.models.Unit;
import org.springframework.stereotype.Component;

@Component
public class UnitMapper {

    public static UnitDto toDTO(Unit unit) {
        UnitDto dto = new UnitDto();
        dto.setId(unit.getId());
        dto.setName(unit.getName());
        dto.setVersion(unit.getVersion());
        return dto;
    }

    public static Unit toEntity(UnitDto dto) {
        Unit unit = new Unit();
        unit.setId(dto.getId());
        unit.setName(dto.getName());
        unit.setVersion(dto.getVersion());
        return unit;
    }
}
