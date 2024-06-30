package com.example.userrolemanagementsystem.services;
import com.example.userrolemanagementsystem.exception.ResourceNotFoundException;
import com.example.userrolemanagementsystem.models.Unit;
import com.example.userrolemanagementsystem.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UnitService {
    @Autowired
    private UnitRepository unitRepository;

    @Transactional(readOnly = true)
    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }

    @Transactional
    public Unit createUnit(Unit unit) {
        return unitRepository.save(unit);
    }

    @Transactional
    public Unit updateUnit(Unit unit) {
        return unitRepository.save(unit);
    }

    @Transactional
    public void deleteUnit(Long unitId) {
        unitRepository.deleteById(unitId);
    }

    public Unit getUnitById(Long unitId) {
        return unitRepository.findById(unitId).orElseThrow(() -> new ResourceNotFoundException("Unit not found with ID: " + unitId));
    }
}