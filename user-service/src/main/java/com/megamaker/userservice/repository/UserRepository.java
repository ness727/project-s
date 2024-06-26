package com.megamaker.userservice.repository;

import com.megamaker.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByProviderId(String providerId);

    Optional<User> findByUserId(String userId);
}
