package com.francis.earthhub.event.attendance.dto;

import java.time.LocalDateTime;

public record AttendanceResponseDTO (
        Long id,
        LocalDateTime registrationDateTime,
        Boolean participated,
        Long eventId,
        Long userId
) {

}
