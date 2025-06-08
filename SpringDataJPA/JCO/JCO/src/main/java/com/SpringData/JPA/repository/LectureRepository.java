package com.SpringData.JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringData.JPA.entity.Lecture;
import com.SpringData.JPA.entity.Video;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer> {
    // This interface will automatically provide CRUD operations for the Lecture entity
    // Additional custom query methods can be defined here if needed

}

