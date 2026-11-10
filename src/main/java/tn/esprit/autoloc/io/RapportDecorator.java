
package tn.esprit.autoloc.io;

import org.springframework.context.annotation.Primary;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Primary
public class RapportDecorator implements Rapport {

    private final Rapport rapport;

    public RapportDecorator(
            @Qualifier("rapportConsole") Rapport rapport) {
        this.rapport = rapport;
    }

    @Override
    public double generer(String entete, double total) {

        System.out.println("===== RAPPORT AGENCE =====");

        return rapport.generer(entete, total);
    }
}
