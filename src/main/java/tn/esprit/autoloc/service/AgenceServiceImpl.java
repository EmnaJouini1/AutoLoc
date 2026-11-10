
package tn.esprit.autoloc.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.io.RapportFactory;
import tn.esprit.autoloc.repository.IAgenceRepository;

@Service
@Primary
public class AgenceServiceImpl
        extends GenericServiceImpl<Agence, Long>
        implements IAgenceService {

    private final IAgenceRepository agenceRepository;
    private final IVehiculeService vehiculeService;
    private final RapportFactory rapportFactory;

    public AgenceServiceImpl(
            IAgenceRepository agenceRepository,
            IVehiculeService vehiculeService,
            RapportFactory rapportFactory) {

        super(agenceRepository);
        this.agenceRepository = agenceRepository;
        this.vehiculeService = vehiculeService;
        this.rapportFactory = rapportFactory;
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

        return Utils.isNotEmpty(agence.getNom())
                && Utils.isNotEmpty(agence.getAdresse())
                && Utils.isNotEmpty(agence.getTelephone());
    }

    @Override
    public Agence addAgence(Agence agence) {
        return create(agence);
    }

    @Override
    public Agence update(Long id, Agence agence) {

        if (agence == null) {
            throw new IllegalArgumentException(
                    "L'agence ne doit pas être null");
        }

        if (!Utils.isNotEmpty(agence.getNom())
                || !Utils.isNotEmpty(agence.getAdresse())
                || !Utils.isNotEmpty(agence.getTelephone())) {

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

    @Override
    public void deleteAgence(Long id) {

        Agence agence = findById(id);

        if (hasVehicules(id)) {
            throw new IllegalArgumentException(
                    "Impossible de supprimer une agence qui possède des véhicules");
        }

        agenceRepository.delete(agence);
    }

    // Simulation du tarif total de l'agence
    @Override
    public String simuler(
            Long idAgence,
            int nombreJours,
            VehiculeOffre offre) {

        Agence agence = findById(idAgence);

        StringBuilder entete = new StringBuilder();

        entete.append("Id : ")
                .append(agence.getIdAgence())
                .append(", Nom : ")
                .append(agence.getNom())
                .append(", Tel : ")
                .append(agence.getTelephone());

        double total = 0;

        if (agence.getVehicules() != null) {
            for (Vehicule vehicule : agence.getVehicules()) {

                total += vehiculeService.calculTarif(
                        vehicule,
                        nombreJours,
                        offre
                );
            }
        }

        double resultat = rapportFactory.creerRapport(
                entete.toString(),
                total
        );

        return String.valueOf(resultat);
    }
}
