package com.example.userrolemanagementsystem.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto  {
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 20, message = "Name must be less than or equal to 20 characters")
    private String name;
    private int version;
}
