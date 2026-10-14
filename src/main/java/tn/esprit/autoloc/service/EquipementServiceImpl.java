package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.repository.IEquipementRepository;

@Service
public class EquipementServiceImpl
        extends GenericServiceImpl<Equipement, Long>
        implements IEquipementService {

    public EquipementServiceImpl(IEquipementRepository repository) {
        super(repository);
    }

    @Override
    protected String getEntityName() {
        return "Equipement";
    }

    @Override
    protected Long getId(Equipement entity) {
        // Remplacer par le vrai getter de l'identifiant
        return null;
    }

    @Override
    protected void setFields(Equipement source, Equipement target) {
        // Ajouter ici les champs modifiables de Equipement
    }
}