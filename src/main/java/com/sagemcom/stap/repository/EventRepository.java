package com.sagemcom.stap.repository;



import com.sagemcom.stap.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // Requête personnalisée pour chercher événements par lieu
    List<Event> findByLieuId(Long lieuId);
}
