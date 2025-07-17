package com.sagemcom.stap.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // <-- IMPORTANT
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private String description;

    private String image;

    private LocalDateTime date;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Transient
    private Long totalTicketsPurchased;

    @Transient
    private Long totalTicketsEntered;

    // ManyToOne vers Lieu (nouvelle relation)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lieu_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "events"})
    private Lieu lieu;

    // Constructeurs
    public Event() {}

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Long getTotalTicketsPurchased() { return totalTicketsPurchased; }
    public void setTotalTicketsPurchased(Long totalTicketsPurchased) { this.totalTicketsPurchased = totalTicketsPurchased; }

    public Long getTotalTicketsEntered() { return totalTicketsEntered; }
    public void setTotalTicketsEntered(Long totalTicketsEntered) { this.totalTicketsEntered = totalTicketsEntered; }

    public Lieu getLieu() { return lieu; }
    public void setLieu(Lieu lieu) { this.lieu = lieu; }
}
