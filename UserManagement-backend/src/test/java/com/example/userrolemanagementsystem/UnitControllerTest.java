package com.example.userrolemanagementsystem;

import com.example.userrolemanagementsystem.controllers.UnitController;
import com.example.userrolemanagementsystem.dtos.UnitDto;
import com.example.userrolemanagementsystem.exception.InvalidRequestException;
import com.example.userrolemanagementsystem.mappers.UnitMapper;
import com.example.userrolemanagementsystem.models.Unit;
import com.example.userrolemanagementsystem.services.UnitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UnitControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UnitController unitController;

    @Mock
    private UnitService unitService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(unitController).build();
    }

    @Test
    public void getAllUnitsTest() throws Exception {
        Unit unit1 = new Unit(1L, "Unit1", 1);
        Unit unit2 = new Unit(2L, "Unit2", 1);
        List<Unit> units = Arrays.asList(unit1, unit2);

        when(unitService.getAllUnits()).thenReturn(units);

        mockMvc.perform(get("/api/units"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Unit1"))
                .andExpect(jsonPath("$[1].name").value("Unit2"));

        verify(unitService, times(1)).getAllUnits();
    }
}
