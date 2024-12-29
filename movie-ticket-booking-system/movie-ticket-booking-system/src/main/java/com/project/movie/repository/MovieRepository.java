package com.project.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.movie.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer>{
	
	Movie findByMovieName(String name); 

}
