package ru.nsu.itboard.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import ru.nsu.itboard.models.Event;
import ru.nsu.itboard.services.EventService;

@RestController
@ComponentScan(value = "ru.nsu.itboard.exceptions")

public class MyEventController {

    private static final String MY_EVENT_PATH = "/events";

    private EventService eventService;

    @Autowired
    public MyEventController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping(MY_EVENT_PATH + "/add")
    @ApiOperation(value = "Добавление мероприятия")
    public Event addEvent(@RequestBody Event event){
        return eventService.addEvent(event);
    }

    @PutMapping(MY_EVENT_PATH  + "/{eventId}/end")
    @ApiOperation(value = "Завершение мероприятия")
    public void endEvent(@PathVariable String eventId){
        eventService.endEvent(eventId);
    }

    @PostMapping(MY_EVENT_PATH + "/{eventId}/edit")
    @ApiOperation(value = "Обновление мероприятия")
    public void editEvent(@PathVariable String eventId, @RequestBody Event event){
        eventService.editEvent(eventId, event);
    }

    @PostMapping(MY_EVENT_PATH + "/{eventId}/{userId}")
    @ApiOperation(value = "Добавление участника мероприятия")
    public void addParticipant(@PathVariable String eventId, @PathVariable String userId){
        eventService.addParticipant(eventId, userId);
    }

    @DeleteMapping(MY_EVENT_PATH + "/{eventId}/{userId}")
    @ApiOperation(value = "Удаление участника мероприятия")
    public void deleteParticipant(@PathVariable String eventId, @PathVariable String userId){
        eventService.deleteParticipant(eventId, userId);
    }

    @DeleteMapping(MY_EVENT_PATH + "/{eventId}")
    @ApiOperation(value = "Удаление мероприятия")
    public void deleteEvent(@PathVariable String eventId){
        eventService.deleteEvent(eventId);
    }

}
