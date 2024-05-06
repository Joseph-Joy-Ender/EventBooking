package com.eventbooking.events.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PaystackTransactionResponse {
    private boolean status;
    private String message;
    @JsonProperty("data")
    private PaystackTransactionDetails paystackTransactionDetails;
}
