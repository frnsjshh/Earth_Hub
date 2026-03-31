package com.francis.earthhub.user.dto;

import com.francis.earthhub.user.Role;

import java.time.LocalDate;

public record UserResponseDTO(
        Long id,
        String email,
        String firstName,
        String lastName,
        Role role,
        LocalDate joinDate
) {
}
