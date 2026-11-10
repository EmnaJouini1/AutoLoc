package tn.esprit.autoloc.calcul;

import org.springframework.stereotype.Component;

@Component("LONGUE_DUREE")
public class LongueDureeStrategy implements IOffreStrategy {

    @Override
    public double calculer(double tarifJournalier, int nombreJours) {

        double total = tarifJournalier * nombreJours * 0.75;

        if (total > 2000) {
            total = total * 0.95;
        }

        return total;
    }
}