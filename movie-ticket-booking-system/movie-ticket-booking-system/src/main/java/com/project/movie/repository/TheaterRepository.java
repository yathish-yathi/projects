package com.project.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.movie.entity.Theater;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {
	
	Theater findByAddress(String address);

}
