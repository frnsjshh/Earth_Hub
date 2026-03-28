package com.francis.earthhub.event;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<VolunteerEvent, Long> {
    Page<VolunteerEvent> findByTitleContainingIgnoreCase(String keyWord, Pageable pageable);
}
