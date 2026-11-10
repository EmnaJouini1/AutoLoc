package tn.esprit.autoloc.calcul;

import org.springframework.stereotype.Component;

@Component("WEEKEND")
public class WeekendStrategy implements IOffreStrategy {

    @Override
    public double calculer(double tarifJournalier, int nombreJours) {
        return tarifJournalier * nombreJours * 0.8;
    }
}