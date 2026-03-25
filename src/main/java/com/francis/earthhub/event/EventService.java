package com.francis.earthhub.event;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        return eventRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
    }
    public List<VolunteerEvent> getAllEvents() {
        return eventRepository.findAll();
    }
    public VolunteerEvent updateEvent(Long id, VolunteerEvent updateRequest) {
        VolunteerEvent existingEvent = eventRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
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
