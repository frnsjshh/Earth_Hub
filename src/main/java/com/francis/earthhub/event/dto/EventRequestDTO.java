package com.francis.earthhub.event.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record EventRequestDTO(
        @NotBlank
        @Size (max = 60, message = "Title cannot exceed 60 characters")
        String title,
        @NotBlank
        String description,
        @NotNull
        LocalDateTime date,
        @NotBlank
        String location,
        @NotNull
        Long organizerId
) {
}
