package com.eventbooking.events.services;

import com.eventbooking.events.data.model.Category;
import com.eventbooking.events.data.model.Event;
import com.eventbooking.events.dtos.request.AddEventRequest;
import com.eventbooking.events.dtos.response.AddEventResponse;
import com.eventbooking.events.exceptions.EventExistException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
@Slf4j
public class EventServiceTest {

    @Autowired
    private EventService eventService;

    @Test
    public void testThatEventCanBeAdded() throws EventExistException {
        AddEventRequest request = new AddEventRequest();
        request.setEventName("Birthday party");
        request.setDate(LocalDate.of(2024, 7, 14));
        request.setAttendees(35);
        request.setDescription("A birthday surprise party for my dearest friend");
        request.setCategory(String.valueOf(Category.BIRTHDAY));


        AddEventResponse response = eventService.createEvent(request);

        assertThat(response).isNotNull();
        assertThat("Birthday party").isEqualTo(request.getEventName());


    }
    @Test
    public void testThatEventCanBeAddedAgain() throws EventExistException {
        AddEventRequest request = new AddEventRequest();
        request.setEventName("Burna Boy Concert");
        request.setDate(LocalDate.of(2024, 11, 3));
        request.setAttendees(1000);
        request.setDescription("Burna Boy concert that's going to be holding at grand square. Be there!!!");
        request.setCategory(String.valueOf(Category.CONCERT));


        AddEventResponse response = eventService.createEvent(request);
        log.info("Add Event Response :: {}", response);
        assertThat(response).isNotNull();
        assertThat("Burna Boy Concert").isEqualTo(request.getEventName());

    }

    @Test
    public void testThatIfSameEventIsAddedExceptionIsThrown() {
        AddEventRequest request = new AddEventRequest();
        request.setEventName("Burna Boy Concert");
        request.setDate(LocalDate.of(2024, 11, 3));
        request.setAttendees(1000);
        request.setDescription("Burna Boy concert that's going to be holding at grand square. Be there!!!");
        request.setCategory(String.valueOf(Category.CONCERT));

        assertThat("Burna Boy Concert").isEqualTo(request.getEventName());
        assertThatExceptionOfType(EventExistException.class).isThrownBy(()-> eventService.createEvent(request));
    }

    @Test
    public void testThatEventCanBeGottenByTheId() throws EventExistException {
        Event event = eventService.findEventBy(1L);

        log.info("Found event :: {}", event);
        assertThat(event).isNotNull();
    }
}
