
package tn.esprit.autoloc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.service.IAgenceService;

@RestController
@RequestMapping("/agences")
@RequiredArgsConstructor
public class AgenceController {

    private final IAgenceService agenceService;

    // Ajouter une agence
    @PostMapping
    public Agence addAgence(@RequestBody Agence agence) {
        return agenceService.addAgence(agence);
    }

    // Récupérer une agence par ID
    @GetMapping("/{id}")
    public Agence getAgence(@PathVariable Long id) {
        return agenceService.findById(id);
    }

    // Récupérer toutes les agences
    @GetMapping
    public List<Agence> getAllAgences() {
        return agenceService.findAll();
    }

    // Modifier une agence
    @PutMapping("/{id}")
    public Agence updateAgence(
            @PathVariable Long id,
            @RequestBody Agence agence) {
        return agenceService.update(id, agence);
    }

    // Supprimer une agence
    @DeleteMapping("/{id}")
    public void deleteAgence(@PathVariable Long id) {
        agenceService.deleteAgence(id);
    }

    // Vérifier si une agence possède des véhicules
    @GetMapping("/{id}/hasVehicules")
    public boolean hasVehicules(@PathVariable Long id) {
        return agenceService.hasVehicules(id);
    }

    // Simuler le tarif d'une agence
    @GetMapping("/{id}/simuler")
    public String simuler(
            @PathVariable Long id,
            @RequestParam int nombreJours,
            @RequestParam VehiculeOffre offre) {

        return agenceService.simuler(id, nombreJours, offre);
    }
}
