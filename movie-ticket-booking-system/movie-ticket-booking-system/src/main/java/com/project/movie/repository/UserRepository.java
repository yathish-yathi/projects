package com.project.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.movie.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByEmailId(String emailId);

}
