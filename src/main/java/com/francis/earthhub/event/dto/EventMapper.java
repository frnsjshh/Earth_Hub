package com.francis.earthhub.event.dto;

import com.francis.earthhub.event.VolunteerEvent;

public class EventMapper {
    public static EventResponseDTO toResponseDTO(VolunteerEvent event) {
        return new EventResponseDTO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
                event.getLocation(),
                event.getOrganizer().getId()
        );
    }
    public static VolunteerEvent toEntity(EventRequestDTO requestDTO) {
        VolunteerEvent event = new VolunteerEvent();
        event.setTitle(requestDTO.title());
        event.setDescription(requestDTO.description());
        event.setLocation(requestDTO.location());
        event.setDate(requestDTO.date());
        return event;
    }
}
