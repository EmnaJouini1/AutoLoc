
package tn.esprit.autoloc.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;

@Service
@RequiredArgsConstructor
public class BasicAgenceServiceImpl implements IAgenceService {

    private final IAgenceRepository agenceRepository;
    private final IVehiculeRepository vehiculeRepository;

    @Override
    public boolean check(Agence agence, boolean isNew) {

        if (agence == null) {
            return false;
        }

        if (isNew && agence.getIdAgence() != null) {
            return false;
        }

        if (!isNew && agence.getIdAgence() == null) {
            return false;
        }

        return Utils.isNotEmpty(agence.getNom())
                && Utils.isNotEmpty(agence.getAdresse())
                && Utils.isNotEmpty(agence.getTelephone());
    }

    @Override
    public Agence create(Agence agence) {

        if (agence == null) {
            throw new IllegalArgumentException(
                    "L'agence ne doit pas être null");
        }

        if (!check(agence, true)) {
            throw new IllegalArgumentException(
                    "Les données de l'agence sont invalides");
        }

        return agenceRepository.save(agence);
    }

    @Override
    public Agence addAgence(Agence agence) {
        return create(agence);
    }

    @Override
    public Agence findById(Long id) {

        return agenceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Agence introuvable avec l'id : " + id));
    }

    @Override
    public List<Agence> findAll() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence update(Long id, Agence agence) {

        if (agence == null) {
            throw new IllegalArgumentException(
                    "L'agence ne doit pas être null");
        }

        Agence agenceExistante = findById(id);

        if (!Utils.isNotEmpty(agence.getNom())
                || !Utils.isNotEmpty(agence.getAdresse())
                || !Utils.isNotEmpty(agence.getTelephone())) {

            throw new IllegalArgumentException(
                    "Les données de l'agence sont invalides");
        }

        agenceExistante.setNom(agence.getNom());
        agenceExistante.setAdresse(agence.getAdresse());
        agenceExistante.setTelephone(agence.getTelephone());

        return agenceRepository.save(agenceExistante);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

        Agence agence = findById(id);

        // Désaffecter les véhicules avant de supprimer l'agence
        if (agence.getVehicules() != null) {

            for (Vehicule vehicule : agence.getVehicules()) {
                vehicule.setAgence(null);
                vehiculeRepository.save(vehicule);
            }

            agence.getVehicules().clear();
        }

        agenceRepository.delete(agence);
    }

    @Override
    @Transactional
    public void deleteAgence(Long id) {
        deleteById(id);
    }

    @Override
    public boolean hasVehicules(Long id) {

        Agence agence = findById(id);

        return agence.getVehicules() != null
                && !agence.getVehicules().isEmpty();
    }

    // Implémentation basique de la simulation
    @Override
    public String simuler(Long idAgence, int nombreJours, VehiculeOffre offre) {

        throw new UnsupportedOperationException(
                "La méthode simuler n'est pas encore implémentée");
    }
}
