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
    private Long eventId;
    @Enumerated(EnumType.STRING)
    private TicketCategory category;
    private BigDecimal price;
    private LocalDate date;
    @ManyToOne(cascade = CascadeType.ALL)
    private Event event;
}
