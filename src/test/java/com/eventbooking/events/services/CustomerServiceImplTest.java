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
import java.time.LocalTime;
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
    public void testThatUserCanCreateAccount2() throws UserException {
        CreateAccountRequest accountRequest = new CreateAccountRequest();
        accountRequest.setName("Udeme Chloe");
        accountRequest.setEmail("udeme5017@gmail.com");
        accountRequest.setPassword("udeme5017");
        CreateAccountResponse response = customerService.createAccount(accountRequest);
        assertThat(response).isNotNull();
        assertThat("udeme5017@gmail.com").isEqualTo(accountRequest.getEmail());
    }
    @Test
    public void testThatUserCanCreateAccount3() throws UserException {
        CreateAccountRequest accountRequest = new CreateAccountRequest();
        accountRequest.setName("Abbey Dollar");
        accountRequest.setEmail("pestmaster4ever@gmail.com");
        accountRequest.setPassword("pestmaster4ever");
        CreateAccountResponse response = customerService.createAccount(accountRequest);
        assertThat(response).isNotNull();
        assertThat("pestmaster4ever@gmail.com").isEqualTo(accountRequest.getEmail());
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
    public void testThatUserCanCreateEvent2() throws UserException, EventExistException {
        AddEventRequest request = new AddEventRequest();
        request.setEventName("Semicolon Women In Tech");
        request.setCategory(String.valueOf(Category.CONFERENCE));
        request.setDescription("A Conference Meeting to bring Women in Semicolon together");
        request.setDate(LocalDate.of(2024, 6, 25));
        request.setAttendees(700);
        request.setEmail("udeme5017@gmail.com");

        AddEventResponse response = customerService.createEvent(request);
        assertThat(response).isNotNull();
    }
@Test
    public void testThatUserCanCreateEvent3() throws UserException, EventExistException {
        AddEventRequest request = new AddEventRequest();
        request.setEventName("Pest Control Annual Event");
        request.setCategory(String.valueOf(Category.CONFERENCE));
        request.setDescription("A Conference Meeting to talk about pest control");
        request.setDate(LocalDate.of(2024, 7, 5));
        request.setAttendees(700);
        request.setEmail("pestmaster4ever@gmail.com");
        request.setTime(LocalTime.of(9, 10));
        request.setLocation("Lagos");

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
