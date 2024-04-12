package com.eventbooking.events.data.repositories;

import com.eventbooking.events.data.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
