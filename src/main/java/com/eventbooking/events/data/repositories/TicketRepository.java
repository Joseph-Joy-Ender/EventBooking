package com.eventbooking.events.data.repositories;

import com.eventbooking.events.data.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findTicketByEventName(String eventName);
    Optional<Ticket> findTicketByReservationId(Long reservationId);

}
