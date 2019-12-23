package ru.nsu.itboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import ru.nsu.itboard.models.Event;
import ru.nsu.itboard.services.EventService;

@RestController
@ComponentScan(value = "ru.nsu.itboard.exceptions")

public class MyEventController {

    private static final String MY_EVENT_PATH = "/my-event";

    private EventService eventService;

    @Autowired
    public MyEventController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping(MY_EVENT_PATH + "/add")
    public Event addEvent(@RequestBody Event event){
        return eventService.addEvent(event);
    }

    @PutMapping(MY_EVENT_PATH  + "/{eventId}/end")
    public void endEvent(@PathVariable String eventId){
        eventService.endEvent(eventId);
    }

    @PostMapping(MY_EVENT_PATH + "/{eventId}/edit")
    public void editEvent(@PathVariable String eventId, @RequestBody Event event){
        eventService.editEvent(eventId, event);
    }

    @PutMapping(MY_EVENT_PATH + "/{eventId}/add/participant/{userId}")
    public void addParticipant(@PathVariable String eventId, @PathVariable String userId){
        eventService.addParticipant(eventId, userId);
    }

    @PutMapping(MY_EVENT_PATH + "/{eventId}/delete/participant/{userId}")
    public void deleteParticipant(@PathVariable String eventId, @PathVariable String userId){
        eventService.deleteParticipant(eventId, userId);
    }

    @DeleteMapping(MY_EVENT_PATH + "/{eventId}/delete")
    public void deleteEvent(@PathVariable String eventId){
        eventService.deleteEvent(eventId);
    }

}
