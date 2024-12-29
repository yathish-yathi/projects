package com.project.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.movie.entity.Show;

public interface ShowRepository extends JpaRepository<Show, Integer>{
	
}
