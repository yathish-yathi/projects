package com.project.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.movie.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
