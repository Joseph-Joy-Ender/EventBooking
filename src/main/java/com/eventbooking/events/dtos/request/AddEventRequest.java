package com.eventbooking.events.dtos.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jdk8.LongStreamSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
public class AddEventRequest {
    @Size(max = 100)
    @NotNull(message = "Event name cannot be blank")
    private String eventName;
    @NotNull(message = "Date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;
    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime time;
    private String location;
    @Max(value = 1000)
    private Integer attendees;
    @Size(max = 500)
    private String description;
    private String category;
    private String email;

}
