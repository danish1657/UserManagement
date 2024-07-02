package com.example.userrolemanagementsystem;

import com.example.userrolemanagementsystem.controllers.UserController;
import com.example.userrolemanagementsystem.dtos.UserDto;
import com.example.userrolemanagementsystem.exception.InvalidRequestException;
import com.example.userrolemanagementsystem.exception.ResourceNotFoundException;
import com.example.userrolemanagementsystem.mappers.UserMapper;
import com.example.userrolemanagementsystem.models.User;
import com.example.userrolemanagementsystem.services.UserService;
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
public class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getAllUsersTest() throws Exception {
        User user1 = new User(1L, "Alice", 1);
        User user2 = new User(2L, "Bob", 1);
        List<User> mockUsers = Arrays.asList(user1, user2);

        //Rule
        when(userService.getAllUsers()).thenReturn(mockUsers);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[1].name").value("Bob"));
    }

    @Test
    public void createUserTest() throws Exception {
        User user = new User(1L, "Alice", 1);
        UserDto userDto = UserMapper.toDto(user);

        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Alice\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Alice"));

    }
}
