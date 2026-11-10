package tn.esprit.autoloc.io;

import org.springframework.stereotype.Component;

@Component
public class RapportFactory {

    private final Rapport rapport;

    public RapportFactory(Rapport rapport) {
        this.rapport = rapport;
    }

    public double creerRapport(String entete, double total) {
        return rapport.generer(entete, total);
    }
}