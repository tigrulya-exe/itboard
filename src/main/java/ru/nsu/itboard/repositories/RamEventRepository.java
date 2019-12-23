package ru.nsu.itboard.repositories;

import org.springframework.stereotype.Repository;
import ru.nsu.itboard.exceptions.NotFoundException;
import ru.nsu.itboard.exceptions.WrongArgumentException;
import ru.nsu.itboard.models.Event;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RamEventRepository implements EventRepository{

    private Map<String, Event> events = new HashMap<>();

    private Map<String, List<String>> eventParticipants = new HashMap<>();

    private Event checkEvent(String eventId){
        Event event = events.get(eventId);
        if(event == null)
            throw new NotFoundException("Wrong userId");
        return event;
    }

    @Override
    public Collection<Event> getAllEvents() {
        return events.values();
    }

    @Override
    public Event addEvent(Event event) {
        if (event == null) {
            throw new WrongArgumentException("Failed to add event. Event is null!");
        }
        event.generateId();
        events.put(event.getId(), event);
        return event;
    }

    @Override
    public Event getEventById(String eventId) {
        return checkEvent(eventId);
    }

    @Override
    public void deleteEvent(String eventId) {
        checkEvent(eventId);
        events.remove(eventId);
    }

    @Override
    public void updateEvent(String eventId, Event newEvent) {
        checkEvent(eventId);
        events.put(eventId, newEvent);
    }
}
