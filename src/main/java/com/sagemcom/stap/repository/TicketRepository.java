package com.sagemcom.stap.repository;

import com.sagemcom.stap.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUserId(Long userId);
    Optional<Ticket> findByIdAndUserId(Long ticketId, Long userId);
    Optional<Ticket> findByUserIdAndEventId(Long userId, Long eventId);


}