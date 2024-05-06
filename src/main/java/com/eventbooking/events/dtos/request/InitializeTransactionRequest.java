package com.eventbooking.events.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class InitializeTransactionRequest {
    private String email;
    private String amount;
}
