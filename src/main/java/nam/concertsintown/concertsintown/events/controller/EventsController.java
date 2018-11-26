package nam.concertsintown.concertsintown.events.controller;

import nam.concertsintown.concertsintown.events.EventNotFoundException;
import nam.concertsintown.concertsintown.events.model.Event;
import nam.concertsintown.concertsintown.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
public class EventsController {

    private final EventRepository eventRepository;

    @Autowired
    public EventsController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/events")
    public Page<Event> getAllEvents(@RequestParam(required = false) String city,Pageable pageable){
        if(!StringUtils.isEmpty(city)){
            return eventRepository.findByCityIgnoreCase(city, pageable);
        }
       return  eventRepository.findAll(pageable);
    }

    @GetMapping("/events/{uuid}")
    public Event getEvent(@PathVariable String uuid){
        return eventRepository.findByUuid(uuid)
                    .orElseThrow(() -> new EventNotFoundException(uuid));
    }

    @PostMapping("/events")
    public ResponseEntity<Event> addEvent(@RequestBody Event newEvent){
        if(newEvent == null) {
            return ResponseEntity.badRequest().build();
        }
        newEvent.setUuid(UUID.randomUUID().toString());
        Event eventAdded = eventRepository.save(newEvent);
        return createResponseEntity(eventAdded);

    }
    @PutMapping("/events/{uuid}")
    public ResponseEntity<Event> editEvent(@PathVariable String uuid, @RequestBody Event newEvent){
        if(newEvent == null) {
            return ResponseEntity.badRequest().build();
        }
        eventRepository.findByUuid(uuid)
                .orElseThrow(() -> new EventNotFoundException(uuid));
        newEvent.setUuid(uuid);
        Event eventEdited = eventRepository.save(newEvent);
        return createResponseEntity(eventEdited);
    }

    @DeleteMapping("/events/{uuid}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String uuid){
        eventRepository.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Event> createResponseEntity(Event event){
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(event.getUuid())
                .toUri();
        return ResponseEntity.created(location).body(event);
    }
}
