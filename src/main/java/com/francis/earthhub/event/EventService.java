package com.francis.earthhub.event;

import com.francis.earthhub.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public VolunteerEvent saveEvent(VolunteerEvent event) {
        return eventRepository.save(event);
    }

    public VolunteerEvent getEventById(Long id) {
        return eventRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Event not found" + id));
    }
    public List<VolunteerEvent> getAllEvents() {
        return eventRepository.findAll();
    }
    public VolunteerEvent updateEvent(Long id, VolunteerEvent updateRequest) {
        VolunteerEvent existingEvent = eventRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Event not found" + id));
        existingEvent.setTitle(updateRequest.getTitle());
        existingEvent.setDescription(updateRequest.getDescription());
        existingEvent.setDate(updateRequest.getDate());
        existingEvent.setLocation(updateRequest.getLocation());
        return eventRepository.save(existingEvent);
    }
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
