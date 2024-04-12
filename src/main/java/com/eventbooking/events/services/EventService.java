package com.eventbooking.events.services;

import com.eventbooking.events.dtos.request.AddEventRequest;
import com.eventbooking.events.dtos.response.AddEventResponse;
import com.eventbooking.events.exceptions.EventExistException;

public interface EventService {
    AddEventResponse createEvent(AddEventRequest request) throws EventExistException;
}
