package com.francis.earthhub.event;

import com.francis.earthhub.exception.OrganizerRoleRequiredException;
import com.francis.earthhub.exception.ResourceNotFoundException;
import com.francis.earthhub.user.AppUser;
import com.francis.earthhub.user.Role;
import com.francis.earthhub.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



@Service
public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    public EventService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public VolunteerEvent saveEvent(VolunteerEvent event, Long organizerId) {
        AppUser organizer = userRepository.findById(organizerId)
                .orElseThrow(() -> new ResourceNotFoundException("Organizer not found with ID: " + organizerId));
        if(event.getOrganizer().getRole()!= Role.ORGANIZER) {
            throw new OrganizerRoleRequiredException("User lacks the required permissions to organize an event.");
        }
        event.setOrganizer(organizer);
        return eventRepository.save(event);
    }

    public VolunteerEvent getEventById(Long id) {
        return eventRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Event not found: " + id));
    }

    public Page<VolunteerEvent> getAllEvents(int page, int size, String sortBy, String keyword) {
        Sort sort = Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        if(keyword != null&& !keyword.isBlank()) {
            return eventRepository.findByTitleContainingIgnoreCase(keyword, pageable);
        }
        return eventRepository.findAll(pageable);
    }
    public VolunteerEvent updateEvent(Long id, VolunteerEvent updateRequest) {
        VolunteerEvent existingEvent = eventRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Event not found: " + id));
        existingEvent.setTitle(updateRequest.getTitle());
        existingEvent.setDescription(updateRequest.getDescription());
        existingEvent.setDate(updateRequest.getDate());
        existingEvent.setLocation(updateRequest.getLocation());
        return eventRepository.save(existingEvent);
    }
    public void deleteEvent(Long id) {
        if(!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event not found" + id);
        }
        eventRepository.deleteById(id);
    }

}
