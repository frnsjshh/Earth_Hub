package com.francis.earthhub.event.dto;



import java.time.LocalDateTime;

public record EventResponseDTO(
        Long id,
        String title,
        String description,
        LocalDateTime date,
        String location,
        Long organizerId
) {
}
