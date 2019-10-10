package com.maraphon.maraphonskills.service;

import com.maraphon.maraphonskills.domain.Event;

import java.util.List;

public interface EventService {

    List<Event> eventList();

    Event findById(String id);

    Event save(Event event);
}
