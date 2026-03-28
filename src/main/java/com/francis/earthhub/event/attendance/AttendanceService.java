package com.francis.earthhub.event.attendance;

import com.francis.earthhub.event.EventRepository;
import com.francis.earthhub.event.VolunteerEvent;
import com.francis.earthhub.exception.DuplicateResourceException;
import com.francis.earthhub.exception.ResourceNotFoundException;
import com.francis.earthhub.user.AppUser;
import com.francis.earthhub.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


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

    public Attendance saveAttendance(Long eventId, Long userId) {
        Attendance attendance = new Attendance();
        VolunteerEvent realEvent = eventRepository.findById(eventId).orElseThrow(()-> new ResourceNotFoundException("Event not found" + eventId));
        AppUser realUser = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found" + userId));
        if(realEvent.getDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Cannot register to an event that has already passed.");
        }
        if(attendanceRepository.existsByUserIdAndEventId(realUser.getId(), realEvent.getId())) {
            throw new DuplicateResourceException("User has already registered for this event");
        }
        attendance.setEvent(realEvent);
        attendance.setUser(realUser);
        return attendanceRepository.save(attendance);
    }


    public Page<Attendance> getAllAttendanceOfTheEvent(int page, int size, String sortBy, Long eventId) {
        Sort sort = Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return attendanceRepository.findByEventId(eventId, pageable);
    }

    public Page<Attendance> getAllAttendanceByUserId(int page, int size, String sortBy, Long userId) {
        Sort sort = Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return attendanceRepository.findByUserId(userId, pageable);
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
