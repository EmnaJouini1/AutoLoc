package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.repository.IMaintenanceRepository;

@Service
public class MaintenanceServiceImpl
        extends GenericServiceImpl<Maintenance, Long>
        implements IMaintenanceService {

    public MaintenanceServiceImpl(IMaintenanceRepository repository) {
        super(repository);
    }

    @Override
    protected String getEntityName() {
        return "Maintenance";
    }

    @Override
    protected Long getId(Maintenance entity) {
        // Remplacer par le vrai getter de l'identifiant
        return null;
    }

    @Override
    protected void setFields(Maintenance source, Maintenance target) {
        // Ajouter ici les champs modifiables de Maintenance
    }
}