package tn.esprit.autoloc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;

import java.math.BigDecimal;
import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AgenceTests {

    @Autowired
    private AgenceRepositoryMock agenceRepository;

    // Étape 7 : Ajouter une agence avec deux véhicules
    @Test
    public void addAgence() {

        // Création de l'agence
        Agence agence = new Agence();

        agence.setNom("Agence ariana");
        agence.setAdresse("1 Rue Hedi");
        agence.setTelephone("71585874");

        // Premier véhicule
        Vehicule vehicule1 = new Vehicule();

        vehicule1.setImmatriculation("785414TU96");
        vehicule1.setMarque("Isuzu");
        vehicule1.setModele("DMax");
        vehicule1.setCategorie(CategorieVehicule.SUV);
        vehicule1.setStatut(StatutVehicule.EN_MAINTENANCE);
        vehicule1.setTarifJournalier(new BigDecimal("100"));
        vehicule1.setAgence(agence);

        // Deuxième véhicule
        Vehicule vehicule2 = new Vehicule();

        vehicule2.setImmatriculation("785414TU95");
        vehicule2.setMarque("Toyota");
        vehicule2.setModele("Yaris");
        vehicule2.setCategorie(CategorieVehicule.UTILITAIRE);
        vehicule2.setStatut(StatutVehicule.DISPONIBLE);
        vehicule2.setTarifJournalier(new BigDecimal("80"));
        vehicule2.setAgence(agence);

        // Association des véhicules à l'agence
        agence.setVehicules(new ArrayList<>());
        agence.getVehicules().add(vehicule1);
        agence.getVehicules().add(vehicule2);

        // Sauvegarde
        agenceRepository.save(agence);
    }

    // Étapes 11, 12, 13, 14 et 15
    @Test
    public void loadAgence() {

        // Récupérer toutes les agences
        Iterable<Agence> agences = agenceRepository.findAll();

        // Construire le résultat
        StringBuilder result = new StringBuilder();

        // Parcourir les agences
        for (Agence agence : agences) {

            // Identifiant de l'agence
            result.append("ID Agence : ")
                    .append(agence.getIdAgence())
                    .append("\n");

            // Nom de l'agence
            result.append("Nom Agence : ")
                    .append(agence.getNom())
                    .append("\n");

            // Nombre de véhicules
            result.append("Nombre de véhicules : ")
                    .append(agence.getVehicules().size())
                    .append("\n");

            // Parcourir les véhicules
            for (Vehicule vehicule : agence.getVehicules()) {

                // Identifiant du véhicule
                result.append("ID Véhicule : ")
                        .append(vehicule.getIdVehicule())
                        .append("\n");

                // Immatriculation
                result.append("Immatriculation : ")
                        .append(vehicule.getImmatriculation())
                        .append("\n");
            }

            result.append("-----------------------------\n");
        }

        // Affichage du résultat
        System.out.println(result);

        // Étape 14 :
        // Assertion qui échoue toujours pour afficher le résultat
        Assert.assertTrue(false);
    }
}