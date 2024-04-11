package com.eventbooking.events.services;

import com.eventbooking.events.data.model.User;
import com.eventbooking.events.data.repositories.UserRepository;
import com.eventbooking.events.dtos.request.CreateAccountRequest;
import com.eventbooking.events.dtos.response.CreateAccountResponse;
import com.eventbooking.events.exceptions.UserException;
import com.eventbooking.events.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest accountRequest) throws UserException {
        if (userRepository.existsByEmail(accountRequest.getEmail())) throw new UserException(GenerateApiResponse.ACCOUNT_WITH_THIS_EMAIL_ALREADY_EXIST);
        User user = mapper.map(accountRequest, User.class);
        userRepository.save(user);
        return GenerateApiResponse.create(GenerateApiResponse.REGISTER_SUCCESSFUL);
    }
}
