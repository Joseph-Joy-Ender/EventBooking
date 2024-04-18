package com.eventbooking.events.services;

import com.eventbooking.events.data.model.Ticket;
import com.eventbooking.events.data.model.User;
import com.eventbooking.events.data.repositories.UserRepository;
import com.eventbooking.events.dtos.request.AddEventRequest;
import com.eventbooking.events.dtos.request.CreateAccountRequest;
import com.eventbooking.events.dtos.response.AddEventResponse;
import com.eventbooking.events.dtos.response.CreateAccountResponse;
import com.eventbooking.events.exceptions.EventExistException;
import com.eventbooking.events.exceptions.TicketException;
import com.eventbooking.events.exceptions.UserException;
import com.eventbooking.events.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final EventService eventService;

    private final TicketService ticketService;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest accountRequest) throws UserException {
        if (userRepository.existsByEmail(accountRequest.getEmail())) throw new UserException(GenerateApiResponse.ACCOUNT_WITH_THIS_EMAIL_ALREADY_EXIST);
        User user = mapper.map(accountRequest, User.class);
        userRepository.save(user);
        return GenerateApiResponse.create(GenerateApiResponse.REGISTER_SUCCESSFUL);
    }

    @Override
    public AddEventResponse createEvent(AddEventRequest request) throws EventExistException, UserException {
        Optional<User> user = userRepository.findUserByEmail(request.getEmail());
        if (user.isPresent()) return eventService.createEvent(request);
        throw  new UserException(GenerateApiResponse.USER_NOT_FOUND);
    }

    @Override
    public List<Ticket> searchTicketBy(String email, String eventName) throws TicketException, UserException {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent()) return ticketService.searchTicketBy(eventName);
        throw new UserException(GenerateApiResponse.USER_NOT_FOUND);
    }
}
