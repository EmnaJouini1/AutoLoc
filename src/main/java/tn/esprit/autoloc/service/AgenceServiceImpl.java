package tn.esprit.autoloc.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IAgenceRepository;

@Service
@Primary
public class AgenceServiceImpl
        extends GenericServiceImpl<Agence, Long>
        implements IAgenceService {

    public AgenceServiceImpl(IAgenceRepository agenceRepository) {
        super(agenceRepository);
    }

    @Override
    protected String getEntityName() {
        return "Agence";
    }

    @Override
    protected Long getId(Agence agence) {
        return agence.getIdAgence();
    }

    @Override
    protected void setFields(Agence source, Agence target) {
        target.setNom(source.getNom());
        target.setAdresse(source.getAdresse());
        target.setTelephone(source.getTelephone());
    }

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

        return tn.esprit.autoloc.Utils.isNotEmpty(agence.getNom())
                && tn.esprit.autoloc.Utils.isNotEmpty(agence.getAdresse())
                && tn.esprit.autoloc.Utils.isNotEmpty(agence.getTelephone());
    }

    @Override
    public Agence update(Long id, Agence agence) {
        if (agence == null) {
            throw new IllegalArgumentException(
                    "L'agence ne doit pas être null");
        }

        if (!tn.esprit.autoloc.Utils.isNotEmpty(agence.getNom())
                || !tn.esprit.autoloc.Utils.isNotEmpty(agence.getAdresse())
                || !tn.esprit.autoloc.Utils.isNotEmpty(agence.getTelephone())) {
            throw new IllegalArgumentException(
                    "Les données de l'agence sont invalides");
        }

        return super.update(id, agence);
    }

    @Override
    public boolean hasVehicules(Long id) {
        Agence agence = findById(id);

        return agence.getVehicules() != null
                && !agence.getVehicules().isEmpty();
    }
}