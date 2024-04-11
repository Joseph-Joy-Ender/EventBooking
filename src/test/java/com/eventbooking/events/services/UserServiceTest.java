package com.eventbooking.events.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testThatUserCanCreateAccount(){
        CreateAccountRequest accountRequest = new CreateAccountRequest();
        accountRequest.setName("Cephas Hemba Ayo Mhenuter");
        accountRequest.setEmail("Cephas123@gmail.com");
        accountRequest.setPassword("Cephas123");
        CreateAccountResponse response = userService.createAccount(accountRequest);
        assertThat(response).isNotNull();

    }
}
