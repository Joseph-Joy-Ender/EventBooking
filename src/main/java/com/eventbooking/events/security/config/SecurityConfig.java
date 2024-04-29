package com.eventbooking.events.security.config;

import com.eventbooking.events.security.filters.AppAuthenticationFilter;
import jakarta.servlet.Filter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static com.eventbooking.events.data.model.Role.CUSTOMER;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        UsernamePasswordAuthenticationFilter filter = new AppAuthenticationFilter(authenticationManager);
        return http.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterAt(filter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(c->c.requestMatchers("/login").permitAll())
                //ADMIN.name()
                .authorizeHttpRequests(c->c.requestMatchers("/api/v1/eventBooking/")
                        .hasAnyAuthority(CUSTOMER.name()))
                .build();
    }


}
