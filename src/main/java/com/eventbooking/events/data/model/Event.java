package com.eventbooking.events.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Setter
@Getter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventName;
    private LocalDate date;
    private Integer attendees;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    private LocalTime time;
    private String location;

}
