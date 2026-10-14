package tn.esprit.autoloc.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IAgenceRepository;

@Service
@RequiredArgsConstructor
public class BasicAgenceServicelmpl implements IAgenceService {

    private final IAgenceRepository agenceRepository;

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
                    "L'agence ne doit pas être null"
            );
        }

        if (agence.getIdAgence() != null) {
            throw new IllegalArgumentException(
                    "L'identifiant doit être null lors de la création"
            );
        }

        if (!check(agence, true)) {
            throw new IllegalArgumentException(
                    "Les données de l'agence sont invalides"
            );
        }

        return agenceRepository.save(agence);
    }

    @Override
    public Agence findById(Long id) {

        return agenceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Agence introuvable avec l'id : " + id
                        )
                );
    }

    @Override
    public List<Agence> findAll() {

        return agenceRepository.findAll();
    }

    @Override
    public Agence update(Long id, Agence agence) {

        if (agence == null) {
            throw new IllegalArgumentException(
                    "L'agence ne doit pas être null"
            );
        }

        Agence agenceExistante = findById(id);

        if (!Utils.isNotEmpty(agence.getNom())
                || !Utils.isNotEmpty(agence.getAdresse())
                || !Utils.isNotEmpty(agence.getTelephone())) {

            throw new IllegalArgumentException(
                    "Les données de l'agence sont invalides"
            );
        }

        agenceExistante.setNom(agence.getNom());
        agenceExistante.setAdresse(agence.getAdresse());
        agenceExistante.setTelephone(agence.getTelephone());

        return agenceRepository.save(agenceExistante);
    }

    @Override
    public void deleteById(Long id) {

        if (!agenceRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Agence introuvable avec l'id : " + id
            );
        }

        agenceRepository.deleteById(id);
    }

    @Override
    public boolean hasVehicules(Long id) {

        Agence agence = findById(id);

        return agence.getVehicules() != null
                && !agence.getVehicules().isEmpty();
    }
}
