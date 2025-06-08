package com.SpringData.JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringData.JPA.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
    // This interface will automatically provide CRUD operations for the Resource entity
    // Additional custom query methods can be defined here if needed
}
