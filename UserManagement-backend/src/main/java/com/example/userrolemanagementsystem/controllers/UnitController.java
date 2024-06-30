package com.example.userrolemanagementsystem.controllers;

import com.example.userrolemanagementsystem.dtos.UnitDto;
import com.example.userrolemanagementsystem.exception.InvalidRequestException;
import com.example.userrolemanagementsystem.mappers.UnitMapper;
import com.example.userrolemanagementsystem.models.Unit;
import com.example.userrolemanagementsystem.services.UnitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/units")
public class UnitController {

    @Autowired
    private UnitService unitService;


    @GetMapping
    public List<UnitDto> getAllUnits() {
        List<Unit> units = unitService.getAllUnits();
        List<UnitDto> unitDTOs = new ArrayList<>();
        for (Unit unit : units) {
            unitDTOs.add(UnitMapper.toDTO(unit));
        }
        return unitDTOs;
    }

    @PostMapping
    public ResponseEntity<UnitDto> createUnit(@Valid @RequestBody UnitDto unitDTO) {
        Unit unit = UnitMapper.toEntity(unitDTO);
        Unit createdUnit = unitService.createUnit(unit);
        UnitDto createdUnitDTO = UnitMapper.toDTO(createdUnit);
        return new ResponseEntity<>(createdUnitDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{unitId}")
    public ResponseEntity<UnitDto> updateUnit(@Valid @PathVariable Long unitId, @RequestBody UnitDto unitDTO) {
        Unit existingUnit = unitService.getUnitById(unitId);
        if (existingUnit.getVersion() != unitDTO.getVersion()) {
            throw new InvalidRequestException("Version mismatch: the provided version does not match the current version.");
        }
        unitDTO.setId(unitId);
        Unit unitToUpdate = UnitMapper.toEntity(unitDTO);
        Unit updatedUnit = unitService.updateUnit(unitToUpdate);
        UnitDto updatedUnitDTO = UnitMapper.toDTO(updatedUnit);
        return new ResponseEntity<>(updatedUnitDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{unitId}")
    public ResponseEntity<Void> deleteUnit(@PathVariable Long unitId) {
        unitService.deleteUnit(unitId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
