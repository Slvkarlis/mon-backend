package com.sagemcom.stap.Service;


import com.sagemcom.stap.Entity.Lieu;
import com.sagemcom.stap.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LieuService {

    @Autowired
    private LieuRepository lieuRepository;

    public List<Lieu> getAllLieux() {
        return lieuRepository.findAll();
    }

    public Optional<Lieu> getLieuById(Long id) {
        return lieuRepository.findById(id);
    }

    public List<Lieu> getLieuxByCategoryId(Long categoryId) {
        return lieuRepository.findByCategoryId(categoryId);
    }

    public Lieu saveLieu(Lieu lieu) {
        return lieuRepository.save(lieu);
    }

    public void deleteLieu(Long id) {
        lieuRepository.deleteById(id);
    }
}
