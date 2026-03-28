package com.francis.earthhub.event.attendance.dto;

import jakarta.validation.constraints.NotNull;

public record AttendanceRequestDTO(
        @NotNull
        Long eventId,
        @NotNull
        Long userId
) {
}
