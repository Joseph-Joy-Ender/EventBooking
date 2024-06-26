package com.eventbooking.events.services;

import com.eventbooking.events.data.model.Category;
import com.eventbooking.events.data.model.Customer;
import com.eventbooking.events.data.model.Ticket;
import com.eventbooking.events.dtos.request.AddEventRequest;
import com.eventbooking.events.dtos.request.CreateAccountRequest;
import com.eventbooking.events.dtos.response.AddEventResponse;
import com.eventbooking.events.dtos.response.CreateAccountResponse;
import com.eventbooking.events.exceptions.EventExistException;
import com.eventbooking.events.exceptions.TicketException;
import com.eventbooking.events.exceptions.UserException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
public class CustomerServiceImplTest {
    @Autowired
    private CustomerService customerService;

    @Test
    public void testThatUserCanCreateAccount() throws UserException {
        CreateAccountRequest accountRequest = new CreateAccountRequest();
        accountRequest.setName("Cephas Hemba Ayo Mhenuter");
        accountRequest.setEmail("Cephas123@gmail.com");
        accountRequest.setPassword("Cephas123");
        CreateAccountResponse response = customerService.createAccount(accountRequest);
        assertThat(response).isNotNull();
        assertThat("Cephas123@gmail.com").isEqualTo(accountRequest.getEmail());

    }
    @Test
    public void testThatUserCanCreateAccountAgain() throws UserException {
        CreateAccountRequest accountRequest = new CreateAccountRequest();
        accountRequest.setName("Joseph Joy Ender Udeme");
        accountRequest.setEmail("joy828545@gmail.com");
        accountRequest.setPassword("Ender123");
        CreateAccountResponse response = customerService.createAccount(accountRequest);
        assertThat(response).isNotNull();
        assertThat("joy828545@gmail.com").isEqualTo(accountRequest.getEmail());

    }

    @Test
    public void testThatUserCanCreateAccountAndCharacterValidation() throws UserException {
        CreateAccountRequest accountRequest = new CreateAccountRequest();
        accountRequest.setName("Favour Mbata Philip");
        accountRequest.setEmail("FavourMbata@gmail.com");
        accountRequest.setPassword("FavourMbataPhilip1234");
        CreateAccountResponse response = customerService.createAccount(accountRequest);
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
        request.setEmail("Cephas123@gmail.com");

        AddEventResponse response = customerService.createEvent(request);
        assertThat(response).isNotNull();
    }

    @Test
    public void testThatUserCanCreateEventAgain() throws UserException, EventExistException {
        AddEventRequest request = new AddEventRequest();
        request.setEventName("End of year conference");
        request.setCategory(String.valueOf(Category.CONFERENCE));
        request.setDescription("A conference that holds every end of year");
        request.setDate(LocalDate.of(2024, 12, 31));
        request.setAttendees(1000);
        request.setEmail("FavourMbata@gmail.com");

        AddEventResponse response = customerService.createEvent(request);
        assertThat(response).isNotNull();
    }

    @Test
    public void testThatUserCanBeSearchedFor() throws UserException {
        Long id = 3L;
        Customer customer = customerService.findById(id);
        assertThat(customer).isNotNull();
        log.info("Found user {}", customer);
    }

}
