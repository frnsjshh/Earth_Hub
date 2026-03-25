package com.francis.earthhub.event;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }


    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }
    //To be implemented - get all attendance of the event
    public List<Attendance> getAllAttendanceOfTheEvent(Long eventId) {
        return attendanceRepository.findByEventId(eventId);
    }
    public List<Attendance> getAllAttendanceByUserId(Long userId) {
        return attendanceRepository.findByUserId(userId);
    }
    public Attendance getAttendanceById(Long id) {
        return attendanceRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendance not found"));
    }

    public Attendance setToPresent(Long id) {
        Attendance attendance = getAttendanceById(id);
        attendance.setParticipated(true);
        return attendanceRepository.save(attendance);
    }

    public Attendance setToAbsent(Long id) {
        Attendance attendance = getAttendanceById(id);
        attendance.setParticipated(false);
        return attendanceRepository.save(attendance);
    }
    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }
}
