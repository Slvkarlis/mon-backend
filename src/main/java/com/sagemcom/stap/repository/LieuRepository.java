package com.sagemcom.stap.repository;


import com.sagemcom.stap.Entity.Lieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LieuRepository extends JpaRepository<Lieu, Long> {
    // Requête personnalisée pour chercher lieux par catégorie
    List<Lieu> findByCategoryId(Long categoryId);
}
