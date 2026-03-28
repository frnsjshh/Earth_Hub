package com.francis.earthhub.event.attendance;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Page<Attendance> findByEventId(Long eventId, Pageable pageable);
    Page<Attendance> findByUserId(Long userId, Pageable pageable);
    boolean existsByUserIdAndEventId(Long userId, Long eventId);

}
