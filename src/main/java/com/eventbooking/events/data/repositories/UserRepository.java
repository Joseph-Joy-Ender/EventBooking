package com.eventbooking.events.data.repositories;

import com.eventbooking.events.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email);
    Optional<Customer> findUserByEmail(String email);
    Optional<Customer> findByEmail(String username);
}
