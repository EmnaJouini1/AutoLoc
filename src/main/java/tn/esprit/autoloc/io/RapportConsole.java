package tn.esprit.autoloc.io;

import org.springframework.stereotype.Component;

@Component("rapportConsole")
public class RapportConsole implements Rapport {

    @Override
    public double generer(String entete, double total) {
        System.out.println(entete);
        return total;
    }
}