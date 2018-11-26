package nam.concertsintown.concertsintown.events.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nam.concertsintown.concertsintown.events.model.Event;
import nam.concertsintown.concertsintown.events.repository.EventRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    EventRepository eventRepository;

    @Test
    public void getAllEvents() throws Exception {
//        this.mockMvc.perform(get("/events?city=rennes&size=3")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().contentType("application/json;charset=UTF-8"))
//        .andExpect(jsonPath("content", hasSize(3)));

        this.mockMvc.perform(get("/events")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void getEventsRennes() throws Exception {
        this.mockMvc.perform(get("/events?city=rennes&size=3")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("content", hasSize(3)));
    }

    @Test
    public void addEvent() throws Exception {
        Event eventToAdd = new Event( "title test", "description test","city test",new Date(), new Date(), null, null, null, null);

        ObjectMapper  objectMapper = new ObjectMapper();
        String eventToAddStr = objectMapper.writeValueAsString(eventToAdd);
    this.mockMvc.perform(post("/events").contentType(MediaType.APPLICATION_JSON_UTF8).content(eventToAddStr))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));


    }





}
