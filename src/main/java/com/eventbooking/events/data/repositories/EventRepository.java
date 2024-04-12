package com.eventbooking.events.data.repositories;

import com.eventbooking.events.data.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface EventRepository extends JpaRepository<Event, Long> {
    Boolean existsByDateAndEventName(LocalDate date, String eventName);
}
