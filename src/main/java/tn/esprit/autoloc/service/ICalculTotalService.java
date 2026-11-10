package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.calcul.VehiculeOffre;

public interface ICalculTotalService {
    double calculerTotal(
            Agence agence,
            int nombreJours,
            VehiculeOffre offre
    );
}
