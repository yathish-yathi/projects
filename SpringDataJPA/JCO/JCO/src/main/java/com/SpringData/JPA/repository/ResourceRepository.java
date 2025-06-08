package com.SpringData.JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringData.JPA.entity.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {
    
}
