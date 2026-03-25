package com.francis.earthhub.event;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public VolunteerEvent saveEvent(@RequestBody VolunteerEvent event) {
        return eventService.saveEvent(event);
    }
    @GetMapping
    public Iterable<VolunteerEvent> getAllEvents() {
        return eventService.getAllEvents();
    }
    @GetMapping("{id}")
    public VolunteerEvent getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }
    @PutMapping("{id}")
    public VolunteerEvent updateEvent(@PathVariable Long id, @RequestBody VolunteerEvent updateRequest) {
        return eventService.updateEvent(id, updateRequest);
    }
    @DeleteMapping("{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}
