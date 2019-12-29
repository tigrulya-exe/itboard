package ru.nsu.itboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.itboard.models.Event;
import ru.nsu.itboard.models.User;
import ru.nsu.itboard.models.UserTo;
import ru.nsu.itboard.repositories.EventRepository;
import ru.nsu.itboard.repositories.UserRepository;
import ru.nsu.itboard.util.EventFilterContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private EventRepository eventRepository;

    private UserRepository userRepository;

    @Autowired
    public EventService(EventRepository eventRepository, UserRepository userRepository){
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public List<Event> getAllEvents(){
        return new ArrayList<>(eventRepository.getAllEvents());
    }

    public List<UserTo> getEventParticipants(String eventId){
        return eventRepository.getEventById(eventId).getParticipants();
    }

    public List<Event> getUserEvents(String userId){
        return eventRepository.getAllEvents()
                .stream()
                .filter(event -> event.getOrganizerId().equals(userId))
                .collect(Collectors.toList());
    }

    public List<Event> getEventsUserTakePartIn(String userId){
        return eventRepository.getAllEvents()
                .stream()
                .filter(event -> event.getParticipants().contains(new UserTo(userId)))
                .collect(Collectors.toList());
    }

    //TODO Add filtration
    public List<Event> getFilteredEvents(String name){
        return getAllEvents().stream()
                .filter(e -> name.equals(e.getName()))
                .collect(Collectors.toList());
    }

    //TODO Add request verification by organizer
    public void takePartRequest(String eventId, String userId){
        addParticipant(eventId, userId);
    }

    public Event addEvent(Event event){
        return eventRepository.addEvent(event);
    }

    public void editEvent(String eventId, Event newEvent){
        eventRepository.updateEvent(eventId, newEvent);
    }

    public void endEvent(String eventId){
        eventRepository.getEventById(eventId).setEnded(true);
    }

    public void addParticipant(String eventId, String userId){
        User user = userRepository.getUser(userId);
        Event event = eventRepository.getEventById(eventId);
        var userTo = new UserTo(user);
        event.getParticipants().add(userTo);
        eventRepository.updateEvent(eventId, event);
    }

    public void deleteParticipant(String eventId, String userId){
        User user = userRepository.getUser(userId);
        Event event = eventRepository.getEventById(eventId);
        event.getParticipants().remove(new UserTo(user));
        eventRepository.updateEvent(eventId, event);
    }

    public void deleteEvent(String eventId){
        eventRepository.deleteEvent(eventId);
    }
}
