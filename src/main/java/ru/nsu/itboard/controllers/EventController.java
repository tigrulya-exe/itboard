package ru.nsu.itboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import ru.nsu.itboard.models.Event;
import ru.nsu.itboard.models.UserTo;
import ru.nsu.itboard.services.EventService;
import ru.nsu.itboard.util.EventFilterContext;

import java.util.List;

@RestController
@ComponentScan(value = "ru.nsu.itboard.exceptions")
public class EventController {

    private static final String EVENT_PATH = "/event";

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @GetMapping(EVENT_PATH + "/list")
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }

    @GetMapping(EVENT_PATH + "/user-{eventId}/participants")
    public List<UserTo> getEventParticipants(@PathVariable String eventId){
        return eventService.getEventParticipants(eventId);
    }

    @GetMapping(EVENT_PATH + "/user-{userId}/org-list")
    public List<Event> getUserEvents(@PathVariable String userId){
        return eventService.getUserEvents(userId);
    }

    @GetMapping(EVENT_PATH + "/user-{userId}/participate-list")
    public List<Event> getEventsUserTakePartIn(@PathVariable String userId){
        return eventService.getEventsUserTakePartIn(userId);
    }

    @GetMapping(EVENT_PATH + "/list/filter")
    public List<Event> getFilteredEvents(EventFilterContext eventFilterContext){
        return eventService.getFilteredEvents(eventFilterContext);
    }

    @PutMapping(EVENT_PATH + "/{eventId}/participate/user-{userId}")
    public void takePartRequest(@PathVariable String eventId, @PathVariable String userId){
        eventService.takePartRequest(eventId, userId);
    }
}
