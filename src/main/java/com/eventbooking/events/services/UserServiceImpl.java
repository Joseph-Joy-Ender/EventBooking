package com.eventbooking.events.services;

import com.eventbooking.events.data.model.Customer;
import com.eventbooking.events.data.model.Ticket;
import com.eventbooking.events.data.repositories.UserRepository;
import com.eventbooking.events.dtos.request.AddEventRequest;
import com.eventbooking.events.dtos.request.CreateAccountRequest;
import com.eventbooking.events.dtos.response.AddEventResponse;
import com.eventbooking.events.dtos.response.CreateAccountResponse;
import com.eventbooking.events.exceptions.EventExistException;
import com.eventbooking.events.exceptions.TicketException;
import com.eventbooking.events.exceptions.UserException;
import com.eventbooking.events.security.user.User;
import com.eventbooking.events.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EventService eventService;
    private final TicketService ticketService;
    private final ModelMapper mapper = new ModelMapper();


    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest accountRequest) throws UserException {
        if (userRepository.existsByEmail(accountRequest.getEmail()))
            throw new UserException(
                GenerateApiResponse.ACCOUNT_WITH_THIS_EMAIL_ALREADY_EXIST);
        Customer customer = mapper.map(accountRequest, Customer.class);
        customer.setPassword(passwordEncoder.encode(accountRequest.getPassword()));
        userRepository.save(customer);
        return GenerateApiResponse.create(GenerateApiResponse.REGISTER_SUCCESSFUL);
    }

    @Override
    public AddEventResponse createEvent(AddEventRequest request) throws EventExistException, UserException {
        Optional<Customer> user = userRepository.findUserByEmail(request.getEmail());
        if (user.isPresent()) return eventService.createEvent(request);
        throw new UserException(GenerateApiResponse.USER_NOT_FOUND);
    }

    @Override
    public Customer findUserBy(String email) throws UserException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(()-> new UserException(GenerateApiResponse.USER_NOT_FOUND));
    }


    @Override
    public Customer findById(Long userId) throws UserException {
        return userRepository.findById(userId)
                .orElseThrow(()-> new UserException(GenerateApiResponse.USER_NOT_FOUND));
    }


    @Override
    public List<Ticket> searchTicketBy(String email, String eventName) throws UserException, TicketException {
        Optional<Customer> customer = userRepository.findUserByEmail(email);
        if (customer.isPresent()) return ticketService.searchTicketBy(eventName);
        throw new UserException(GenerateApiResponse.USER_NOT_FOUND);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Customer customer = userRepository.findByEmail(username)
               .orElseThrow(()->new UserException(String.format("User with username %s not found", username)));
        return new User(customer);
    }
}
