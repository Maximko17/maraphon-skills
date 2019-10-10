package com.maraphon.maraphonskills.service;

import com.maraphon.maraphonskills.domain.Event;
import com.maraphon.maraphonskills.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> eventList() {
        List<Event> events = new ArrayList<>();
        eventRepository.findAll().forEach(events::add);

        return events;
    }

    @Override
    public Event findById(String id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }
}
