package com.eventbooking.events.dtos.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateAccountRequest {
//    @Min(100)
//    @NotBlank(message = "Name cannot be blank")
//    @NotEmpty(message = "Name cannot be empty")
    private String name;
//    @NotBlank(message = "Password cannot be blank")
//    @NotEmpty(message = "Password cannot be empty")
//    @Min(8)
    private String password;
//    @NotBlank
//    @NotEmpty
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
    private String email;
}
