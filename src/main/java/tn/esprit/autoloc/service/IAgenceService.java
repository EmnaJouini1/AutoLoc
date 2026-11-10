package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.calcul.VehiculeOffre;

public interface IAgenceService extends IGenericService<Agence, Long> {

    Agence addAgence(Agence agence);

    boolean hasVehicules(Long id);

    void deleteAgence(Long id);

    String simuler(Long idAgence, int nombreJours, VehiculeOffre offre);
}