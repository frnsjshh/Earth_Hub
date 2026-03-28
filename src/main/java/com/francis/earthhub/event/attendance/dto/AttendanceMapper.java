package com.francis.earthhub.event.attendance.dto;

import com.francis.earthhub.event.VolunteerEvent;
import com.francis.earthhub.event.attendance.Attendance;
import com.francis.earthhub.exception.ResourceNotFoundException;
import com.francis.earthhub.user.AppUser;

import java.util.Optional;

public class AttendanceMapper {
    public static AttendanceResponseDTO toResponseDTO (Attendance attendance) {
        if (attendance == null) {
            throw new IllegalArgumentException("Attendance not found");
        }
        Long eventId = Optional.ofNullable(attendance.getEvent()).map(VolunteerEvent::getId).orElseThrow(() -> new ResourceNotFoundException("Event data is missing."));
        Long userId = Optional.ofNullable(attendance.getUser()).map(AppUser::getId).orElseThrow(() -> new ResourceNotFoundException("User data is missing."));
        return new AttendanceResponseDTO(
                attendance.getId(),
                attendance.getRegistrationDateTime(),
                attendance.getParticipated(),
                eventId,
                userId
        );
    }
    // No need to map to entity
}
