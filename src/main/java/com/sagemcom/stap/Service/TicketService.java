package com.sagemcom.stap.Service;

import com.sagemcom.stap.Entity.Event;
import com.sagemcom.stap.Entity.Ticket;
import com.sagemcom.stap.repository.EventRepository;
import com.sagemcom.stap.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventRepository eventRepository;

    public List<Ticket> getTicketsByUserId(Long userId) {
        return ticketRepository.findByUserId(userId);
    }

    public Optional<Ticket> getTicketByIdAndUserId(Long ticketId, Long userId) {
        return ticketRepository.findByIdAndUserId(ticketId, userId);
    }

    public Ticket createTicket(Ticket ticket, Long userId) {
        ticket.setUserId(userId);
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setUpdatedAt(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Long ticketId, Long userId, Ticket updateData) {
        Optional<Ticket> optionalTicket = ticketRepository.findByIdAndUserId(ticketId, userId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setEntered(updateData.isEntered());
            ticket.setUpdatedAt(LocalDateTime.now());
            return ticketRepository.save(ticket);
        } else {
            throw new RuntimeException("Ticket not found");
        }
    }

    public void deleteTicket(Long ticketId, Long userId) {
        Optional<Ticket> optionalTicket = ticketRepository.findByIdAndUserId(ticketId, userId);
        optionalTicket.ifPresent(ticketRepository::delete);
    }

    public Ticket purchaseTicket(Long userId, Long eventId) {
        // Utilise bien l'instance eventRepository ici (pas la classe EventRepository)
        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new RuntimeException("Événement non trouvé"));

        // Vérifie que l'utilisateur n'a pas déjà un ticket pour cet événement
        Optional<Ticket> existing = ticketRepository.findByUserIdAndEventId(userId, eventId);
        if (existing.isPresent()) {
            throw new RuntimeException("Ticket déjà acheté pour cet événement.");
        }

        Ticket ticket = new Ticket();
        ticket.setUserId(userId);
        ticket.setEvent(event);
        ticket.setEntered(false);
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setUpdatedAt(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }
}
