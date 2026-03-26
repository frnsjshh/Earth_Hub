package com.francis.earthhub.user.dto;

import com.francis.earthhub.user.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotBlank(message = "First name is required")
        @Size(max = 150, message = "First name cannot exceed 150 characters")
        String firstName,
        @NotBlank(message = "Last name is required")
        @Size(max = 150, message = "Last name cannot exceed 150 characters")
        String lastName,
        @NotNull (message = "Role is required")
        Role role
) {
}
