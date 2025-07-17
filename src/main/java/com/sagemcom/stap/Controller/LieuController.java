package com.sagemcom.stap.Controller;

import com.sagemcom.stap.Entity.Lieu;
import com.sagemcom.stap.Service.LieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lieux")

public class LieuController {

    @Autowired
    private LieuService lieuService;

    @GetMapping
    public List<Lieu> getAllLieux() {
        return lieuService.getAllLieux();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lieu> getLieuById(@PathVariable Long id) {
        return lieuService.getLieuById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-category/{categoryId}")
    public List<Lieu> getLieuxByCategory(@PathVariable Long categoryId) {
        return lieuService.getLieuxByCategoryId(categoryId);
    }

    @PostMapping
    public Lieu createLieu(@RequestBody Lieu lieu) {
        return lieuService.saveLieu(lieu);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLieu(@PathVariable Long id) {
        lieuService.deleteLieu(id);
        return ResponseEntity.noContent().build();
    }
}
