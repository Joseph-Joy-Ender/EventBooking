package com.eventbooking.events.services;

import com.eventbooking.events.data.model.Category;
import com.eventbooking.events.dtos.request.AddEventRequest;
import com.eventbooking.events.dtos.request.CreateAccountRequest;
import com.eventbooking.events.dtos.response.AddEventResponse;
import com.eventbooking.events.dtos.response.CreateAccountResponse;
import com.eventbooking.events.exceptions.EventExistException;
import com.eventbooking.events.exceptions.UserException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testThatUserCanCreateAccount() throws UserException {
        CreateAccountRequest accountRequest = new CreateAccountRequest();
        accountRequest.setName("Cephas Hemba Ayo Mhenuter");
        accountRequest.setEmail("Cephas123@gmail.com");
        accountRequest.setPassword("Cephas123");
        CreateAccountResponse response = userService.createAccount(accountRequest);
        assertThat(response).isNotNull();
        assertThat("Cephas123@gmail.com").isEqualTo(accountRequest.getEmail());

    }

    @Test
    public void testThatUserCanCreateAccountAndCharacterValidation() throws UserException {
        CreateAccountRequest accountRequest = new CreateAccountRequest();
        accountRequest.setName("Favour Mbata Philip");
        accountRequest.setEmail("FavourMbata@gmail.com");
        accountRequest.setPassword("FavourMbataPhilip1234");
        CreateAccountResponse response = userService.createAccount(accountRequest);
        assertThat(response).isNotNull();
        assertThat("FavourMbata@gmail.com").isEqualTo(accountRequest.getEmail());

    }

    @Test
    public void testThatUserCanCreateEvent() throws UserException, EventExistException {
        AddEventRequest request = new AddEventRequest();
        request.setEventName("Table Tennis");
        request.setCategory(String.valueOf(Category.GAME));
        request.setDescription("A game for everyone");
        request.setDate(LocalDate.of(2024, 4, 20));
        request.setAttendees(700);

        AddEventResponse response = userService.createEvent(request, "Cephas123@gmail.com");
        assertThat(response).isNotNull();
    }

    @Test
    public void testThatTicketCanBeAddedForEventAdded(){

    }
}
