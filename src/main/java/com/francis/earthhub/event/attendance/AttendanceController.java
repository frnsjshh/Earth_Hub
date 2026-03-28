package com.francis.earthhub.event.attendance;

import com.francis.earthhub.event.attendance.dto.AttendanceMapper;
import com.francis.earthhub.event.attendance.dto.AttendanceRequestDTO;
import com.francis.earthhub.event.attendance.dto.AttendanceResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public AttendanceResponseDTO saveAttendance(@Valid @RequestBody AttendanceRequestDTO attendanceDTO) {
        return AttendanceMapper.toResponseDTO(attendanceService.saveAttendance(attendanceDTO.eventId(), attendanceDTO.userId()));
    }

    @GetMapping("/{id}")
    public AttendanceResponseDTO getAttendanceById(@PathVariable Long id) {
        return AttendanceMapper.toResponseDTO(attendanceService.getAttendanceById(id));
    }

    @GetMapping("/user/{userId}")
    public Page<AttendanceResponseDTO> getAllAttendanceByUserId(
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size,
            @RequestParam (defaultValue = "registrationDateTime") String sortBy,
            @PathVariable Long userId
    ) {
        return attendanceService.getAllAttendanceByUserId(page, size, sortBy, userId).map(AttendanceMapper::toResponseDTO);
    }
    @GetMapping("/event/{eventId}")
    public Page<AttendanceResponseDTO> getAllAttendanceOfTheEvent(
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size,
            @RequestParam (defaultValue = "registrationDateTime") String sortBy,
            @PathVariable Long eventId
    ) {
        return attendanceService.getAllAttendanceOfTheEvent(page, size, sortBy, eventId).map(AttendanceMapper::toResponseDTO);
    }


    @PutMapping("/{id}/present")
    public AttendanceResponseDTO setToPresent(@PathVariable Long id) {
        return AttendanceMapper.toResponseDTO(attendanceService.setToPresent(id));
    }
    @PutMapping("/{id}/absent")
    public AttendanceResponseDTO setToAbsent(@PathVariable Long id) {
        return AttendanceMapper.toResponseDTO(attendanceService.setToAbsent(id));
    }
    @DeleteMapping("/{id}")
    public void deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
    }

}
