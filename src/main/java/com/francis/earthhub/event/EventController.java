package com.francis.earthhub.event;

import com.francis.earthhub.event.dto.EventMapper;
import com.francis.earthhub.event.dto.EventRequestDTO;
import com.francis.earthhub.event.dto.EventResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public EventResponseDTO saveEvent(@Valid @RequestBody EventRequestDTO eventDTO) {
        return EventMapper.toResponseDTO(eventService.saveEvent(EventMapper.toEntity(eventDTO), eventDTO.organizerId()));
    }
    @GetMapping
    public Page<EventResponseDTO> getAllEvents(
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size,
            @RequestParam (defaultValue = "date") String sortBy,
            @RequestParam (required = false) String keyword
    ) {
        return eventService.getAllEvents(page, size, sortBy,keyword).map(EventMapper::toResponseDTO);
    }
    @GetMapping("/{id}")
    public EventResponseDTO getEventById(@PathVariable Long id) {
        return EventMapper.toResponseDTO(eventService.getEventById(id));
    }
    @PutMapping("/{id}")
    public EventResponseDTO updateEvent(@PathVariable Long id, @Valid @RequestBody EventRequestDTO updateRequest) {
        return EventMapper.toResponseDTO(eventService.updateEvent(id, EventMapper.toEntity(updateRequest)));
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}
