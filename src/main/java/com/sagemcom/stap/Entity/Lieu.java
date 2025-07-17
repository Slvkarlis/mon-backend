package com.sagemcom.stap.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lieux")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // <-- IMPORTANT
public class Lieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String adresse;

    private String image;

    // ManyToOne vers Category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("lieux") // Ignore la liste lieux dans category pour éviter boucle
    private Category category;

    // OneToMany vers Event (optionnel)
    @OneToMany(mappedBy = "lieu", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("lieu")  // Ignore la propriété lieu dans Event pour éviter boucle
    private List<Event> events;

    // Constructeurs
    public Lieu() {}

    public Lieu(String nom, String adresse, String image, Category category) {
        this.nom = nom;
        this.adresse = adresse;
        this.image = image;
        this.category = category;
    }

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public List<Event> getEvents() { return events; }
    public void setEvents(List<Event> events) { this.events = events; }
}
