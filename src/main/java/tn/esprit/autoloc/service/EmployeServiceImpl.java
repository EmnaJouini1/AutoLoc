package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.IEmployeRepository;

@Service
public class EmployeServiceImpl
        extends GenericServiceImpl<Employe, Long>
        implements IEmployeService {

    public EmployeServiceImpl(IEmployeRepository repository) {
        super(repository);
    }

    @Override
    protected String getEntityName() {
        return "Employe";
    }

    @Override
    protected Long getId(Employe entity) {
        // Remplacer par le vrai getter de l'identifiant
        return null;
    }

    @Override
    protected void setFields(Employe source, Employe target) {
        // Ajouter ici les champs modifiables de Employe
    }
}