package com.eventbooking.events.services;

import com.eventbooking.events.data.model.Event;
import com.eventbooking.events.data.repositories.EventRepository;
import com.eventbooking.events.dtos.request.AddEventRequest;
import com.eventbooking.events.dtos.response.AddEventResponse;
import com.eventbooking.events.exceptions.EventExistException;
import com.eventbooking.events.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;

    private final ModelMapper mapper = new ModelMapper();
    @Override
    public AddEventResponse createEvent(AddEventRequest request) throws EventExistException {
        if (eventRepository.existsByDateAndEventName(request.getDate(), request.getEventName())){
            throw new EventExistException(GenerateApiResponse.EVENT_WITH_THIS_DATE_AND_NAME_EXIST);
        }

        Event event = mapper.map(request, Event.class);
        eventRepository.save(event);

        AddEventResponse response = new AddEventResponse();
        response.setId(event.getId());
        response.setEventName(request.getEventName());
        response.setDate(request.getDate());
        return response;
    }
}
