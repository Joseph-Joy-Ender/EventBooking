package com.eventbooking.events.data.repositories;

import com.eventbooking.events.data.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findTicketByEventName(String eventName);
    Ticket findTicketByReservationId(Long reservationId);
}
