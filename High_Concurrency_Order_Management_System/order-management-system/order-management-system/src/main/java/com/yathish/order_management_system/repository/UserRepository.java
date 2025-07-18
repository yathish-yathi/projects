package com.yathish.order_management_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yathish.order_management_system.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
    
}
