package com.eventbooking.events.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TicketCategory category;
    private BigDecimal price;
    private LocalDate eventDate;
    private String eventName;
    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_status", length = 30)
    private TicketStatus ticketStatus;
    @ManyToOne
    private Customer customer;
    @Column(unique = true)
    private Long reservationId;

}
