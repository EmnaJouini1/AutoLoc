
package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.calcul.IOffreStrategy;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.util.Map;

@Service
public class VehiculeServiceImpl
        extends GenericServiceImpl<Vehicule, Long>
        implements IVehiculeService {

    // Injection des strategies avec Spring IoC
    private final Map<String, IOffreStrategy> strategies;

    public VehiculeServiceImpl(
            IVehiculeRepository repository,
            Map<String, IOffreStrategy> strategies) {

        super(repository);
        this.strategies = strategies;
    }

    @Override
    protected String getEntityName() {
        return "Vehicule";
    }

    @Override
    protected Long getId(Vehicule entity) {
        return entity.getIdVehicule();
    }

    @Override
    protected void setFields(Vehicule source, Vehicule existing) {
        existing.setImmatriculation(source.getImmatriculation());
        existing.setMarque(source.getMarque());
        existing.setModele(source.getModele());
        existing.setCategorie(source.getCategorie());
        existing.setTarifJournalier(source.getTarifJournalier());
        existing.setStatut(source.getStatut());
    }

    @Override
    public boolean check(Vehicule v, boolean isNew) {
        return v != null
                && Utils.isNotEmpty(v.getImmatriculation())
                && Utils.isNotEmpty(v.getMarque())
                && Utils.isNotEmpty(v.getModele())
                && v.getTarifJournalier() != null;
    }

    // Calcul du tarif selon la strategie choisie
    @Override
    public double calculTarif(
            Vehicule vehicule,
            int nbJours,
            VehiculeOffre offre) {

        double tarifJournalier =
                vehicule.getTarifJournalier().doubleValue();

        IOffreStrategy strategy = strategies.get(offre.name());

        if (strategy == null) {
            throw new IllegalArgumentException(
                    "Offre inconnue : " + offre
            );
        }

        return strategy.calculer(tarifJournalier, nbJours);
    }
}
