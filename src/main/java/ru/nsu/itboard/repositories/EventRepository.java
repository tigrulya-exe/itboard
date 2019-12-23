package ru.nsu.itboard.repositories;

import ru.nsu.itboard.models.Event;

import java.util.Collection;

public interface EventRepository {

    Collection<Event> getAllEvents();

    Event addEvent(Event event);

    void deleteEvent(String eventId);

    void updateEvent(String eventId, Event event);

    Event getEventById(String eventId);

}
