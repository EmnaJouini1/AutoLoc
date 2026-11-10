
package tn.esprit.autoloc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

@RestController
@RequestMapping("/vehicules")
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    public VehiculeController(IVehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    // Afficher tous les véhicules
    @GetMapping
    public List<Vehicule> getAllVehicules() {
        return vehiculeService.findAll();
    }

    // Afficher un véhicule par ID
    @GetMapping("/{id}")
    public Vehicule getVehiculeById(@PathVariable Long id) {
        return vehiculeService.findById(id);
    }

    // Ajouter un véhicule
    @PostMapping
    public Vehicule addVehicule(@RequestBody Vehicule vehicule) {
        return vehiculeService.create(vehicule);
    }

    // Modifier un véhicule
    @PutMapping("/{id}")
    public Vehicule updateVehicule(
            @PathVariable Long id,
            @RequestBody Vehicule vehicule) {
        return vehiculeService.update(id, vehicule);
    }

    // Supprimer un véhicule
    @DeleteMapping("/{id}")
    public void deleteVehicule(@PathVariable Long id) {
        vehiculeService.deleteById(id);
    }
}
