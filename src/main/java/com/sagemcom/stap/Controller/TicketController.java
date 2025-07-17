package com.sagemcom.stap.Controller;


import com.sagemcom.stap.Entity.Ticket;
import com.sagemcom.stap.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // Récupérer tous les tickets d’un utilisateur
    @GetMapping
    public List<Ticket> getTickets(@PathVariable Long userId) {
        List<Ticket> tickets = ticketService.getTicketsByUserId(userId);
        return  tickets;
    }

    // Récupérer un ticket par ID et userID
    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long userId, @PathVariable Long ticketId) {
        return ticketService.getTicketByIdAndUserId(ticketId, userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Créer un ticket
    @PostMapping
    public ResponseEntity<Ticket> createTicket(@PathVariable Long userId, @RequestBody Ticket ticket) {
        Ticket created = ticketService.createTicket(ticket, userId);
        return ResponseEntity.status(201).body(created);
    }

    // Mettre à jour un ticket
    @PutMapping("/{ticketId}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long userId, @PathVariable Long ticketId, @RequestBody Ticket updateData) {
        try {
            Ticket updated = ticketService.updateTicket(ticketId, userId, updateData);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un ticket
    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long userId, @PathVariable Long ticketId) {
        ticketService.deleteTicket(ticketId, userId);
        return ResponseEntity.noContent().build();
    }
    // Acheter un ticket pour un événement
@PostMapping("/purchase")
public ResponseEntity<Ticket> purchaseTicket(
        @PathVariable Long userId,
        @RequestParam Long eventId
) {
    try {
        Ticket ticket = ticketService.purchaseTicket(userId, eventId);
        return ResponseEntity.status(201).body(ticket);
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(null);
    }
}

}