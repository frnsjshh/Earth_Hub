package com.francis.earthhub.event;

import com.francis.earthhub.exception.DuplicateResourceException;
import com.francis.earthhub.exception.ResourceNotFoundException;
import com.francis.earthhub.user.AppUser;
import com.francis.earthhub.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;


    public AttendanceService(AttendanceRepository attendanceRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.attendanceRepository = attendanceRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public Attendance saveAttendance(Attendance attendance) {
        VolunteerEvent realEvent = eventRepository.findById(attendance.getEvent().getId()).orElseThrow(()-> new ResourceNotFoundException("Event not found" + attendance.getEvent().getId()));
        AppUser realUser = userRepository.findById(attendance.getUser().getId()).orElseThrow(()-> new ResourceNotFoundException("User not found" + attendance.getUser().getId()));
        if(realEvent.getDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Cannot register to an event that has already passed.");
        }
        if(attendanceRepository.existsByUserIdAndEventId(realUser.getId(), realEvent.getId())) {
            throw new DuplicateResourceException("User has already registered for this event");
        }
        attendance.setEvent(realEvent);
        return attendanceRepository.save(attendance);
    }
    public List<Attendance> getAllAttendanceOfTheEvent(Long eventId) {
        return attendanceRepository.findByEventId(eventId);
    }
    public List<Attendance> getAllAttendanceByUserId(Long userId) {
        return attendanceRepository.findByUserId(userId);
    }
    public Attendance getAttendanceById(Long id) {
        return attendanceRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Attendance not found" + id));
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
