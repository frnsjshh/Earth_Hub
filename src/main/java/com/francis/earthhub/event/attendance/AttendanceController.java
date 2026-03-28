package com.francis.earthhub.event.attendance;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public Attendance saveAttendance(@RequestBody Attendance attendance) {
        return attendanceService.saveAttendance(attendance);
    }

    @GetMapping("/{id}")
    public Attendance getAttendanceById(@PathVariable Long id) {
        return attendanceService.getAttendanceById(id);
    }
    @GetMapping("/user/{userId}")
    public List<Attendance> getAllAttendanceByUserId(@PathVariable Long userId) {
        return attendanceService.getAllAttendanceByUserId(userId);
    }
    @GetMapping("/event/{eventId}")
    public List<Attendance> getAllAttendanceOfTheEvent(@PathVariable Long eventId) {
        return attendanceService.getAllAttendanceOfTheEvent(eventId);
    }


    @PutMapping("/{id}/present")
    public Attendance setToPresent(@PathVariable Long id) {
        return attendanceService.setToPresent(id);
    }
    @PutMapping("/{id}/absent")
    public Attendance setToAbsent(@PathVariable Long id) {
        return attendanceService.setToAbsent(id);
    }
    @DeleteMapping("/{id}")
    public void deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
    }

}
