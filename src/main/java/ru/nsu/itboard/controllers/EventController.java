package ru.nsu.itboard.controllers;

import io.swagger.annotations.ApiOperation;
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

    private static final String EVENT_PATH = "/events";

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @GetMapping(EVENT_PATH)
    @ApiOperation(value = "Получение всех мероприятий")
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }

    @GetMapping(EVENT_PATH + "/{eventId}/participants")
    @ApiOperation(value = "Получение всех участников мероприятия")
    public List<UserTo> getEventParticipants(@PathVariable String eventId){
        return eventService.getEventParticipants(eventId);
    }

    @GetMapping(EVENT_PATH + "/{userId}/events")
    @ApiOperation(value = "Получение всех мероприятий конкретного пользователя")
    public List<Event> getUserEvents(@PathVariable String userId){
        return eventService.getUserEvents(userId);
    }

    @GetMapping(EVENT_PATH + "/{userId}/takeParts")
    @ApiOperation(value = "Получение всех мероприятий в которых участвовал конкретный пользователь")
    public List<Event> getEventsUserTakePartIn(@PathVariable String userId){
        return eventService.getEventsUserTakePartIn(userId);
    }

    @GetMapping(EVENT_PATH + "/search")
    @ApiOperation(value = "Получение мероприятий, подходящих определенным условиям")
    public List<Event> getFilteredEvents(@RequestParam String name){
        return eventService.getFilteredEvents(name);
    }

    @PutMapping(EVENT_PATH + "/{eventId}/participate")
    @ApiOperation(value = "Запрос на участие в мероприятии")
    public void takePartRequest(@PathVariable String eventId,
                                @RequestParam String user){
        eventService.takePartRequest(eventId, user);
    }
}
