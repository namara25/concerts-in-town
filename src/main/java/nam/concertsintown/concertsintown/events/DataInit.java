package nam.concertsintown.concertsintown.events;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nam.concertsintown.concertsintown.events.model.Event;
import nam.concertsintown.concertsintown.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class DataInit {

    private final EventRepository eventRepository;

    @Autowired
    public DataInit(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @PostConstruct
    public void init() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("events.json").getFile());
            List<Event> events = objectMapper.readValue( file, new TypeReference<List<Event>>(){});
            this.eventRepository.saveAll(events);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
