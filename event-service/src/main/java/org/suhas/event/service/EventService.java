package org.suhas.event.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.suhas.event.domain.Event;
import org.suhas.event.dto.EventRequest;
import org.suhas.event.repository.EventRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    @CacheEvict(value = "allEvents",allEntries = true)
    public Event createEvent(EventRequest request){
        Event event = Event.builder()
                .id(UUID.randomUUID().toString())
                .name(request.name())
                .description(request.description())
                .date(request.date())
                .location(request.location())
                .totalSeats(request.totalSeats())
                .availableSeats(request.totalSeats()) // Initially, all seats are available
                .price(request.price())
                .build();
        return eventRepository.save(event);
    }

    @Cacheable(value = "allEvents")
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Cacheable(value = "event", key = "#id")
    public Event getEventById(String id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }


}
