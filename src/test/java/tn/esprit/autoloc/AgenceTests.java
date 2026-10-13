
package tn.esprit.autoloc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IAgenceRepository;

import java.math.BigDecimal;
import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AgenceTests {

    @Autowired
    private IAgenceRepository basicAgenceRepository;

    @Autowired
    private IAgenceRepository fullAgenceRepository;

    // 1. Ajouter une agence et ses véhicules
    private void addAgence(CrudRepository<Agence, Long> repository) {

        Agence agence = new Agence();
        agence.setNom("Agence ariana");
        agence.setAdresse("1 Rue Hedi");
        agence.setTelephone("71585874");

        int info = (int) (System.currentTimeMillis() % 100000);

        Vehicule vehicule1 = new Vehicule();
        vehicule1.setImmatriculation("785414TU" + info);
        vehicule1.setMarque("Isuzu");
        vehicule1.setModele("DMax");
        vehicule1.setCategorie(CategorieVehicule.SUV);
        vehicule1.setStatut(StatutVehicule.EN_MAINTENANCE);
        vehicule1.setTarifJournalier(new BigDecimal("100"));
        vehicule1.setAgence(agence);

        Vehicule vehicule2 = new Vehicule();
        vehicule2.setImmatriculation("785415TU" + info);
        vehicule2.setMarque("Toyota");
        vehicule2.setModele("Yaris");
        vehicule2.setCategorie(CategorieVehicule.UTILITAIRE);
        vehicule2.setStatut(StatutVehicule.DISPONIBLE);
        vehicule2.setTarifJournalier(new BigDecimal("80"));
        vehicule2.setAgence(agence);

        agence.setVehicules(new ArrayList<>());
        agence.getVehicules().add(vehicule1);
        agence.getVehicules().add(vehicule2);

        repository.save(agence);
    }

    @Test
    public void basicAddAgence() {
        addAgence(basicAgenceRepository);
    }

    @Test
    public void fullAddAgence() {
        addAgence(fullAgenceRepository);
    }

    // 2. Charger les agences et leurs véhicules
    private void loadAgence(
            CrudRepository<Agence, Long> repository,
            String typeDepot) {

        System.out.println("\nType de dépôt : " + typeDepot);

        Iterable<Agence> agences = repository.findAll();

        for (Agence agence : agences) {

            System.out.println("ID Agence : " + agence.getIdAgence());
            System.out.println("Nom Agence : " + agence.getNom());
            System.out.println("Adresse : " + agence.getAdresse());
            System.out.println("Téléphone : " + agence.getTelephone());

            if (agence.getVehicules() != null) {

                System.out.println("Nombre de véhicules : "
                        + agence.getVehicules().size());

                for (Vehicule vehicule : agence.getVehicules()) {
                    System.out.println("ID Véhicule : "
                            + vehicule.getIdVehicule());
                    System.out.println("Immatriculation : "
                            + vehicule.getImmatriculation());
                    System.out.println("Marque : " + vehicule.getMarque());
                    System.out.println("Modèle : " + vehicule.getModele());
                    System.out.println("Catégorie : " + vehicule.getCategorie());
                    System.out.println("Statut : " + vehicule.getStatut());
                    System.out.println("Tarif journalier : "
                            + vehicule.getTarifJournalier());
                }
            }

            System.out.println("--------------------------------");
        }
    }

    @Test
    public void basicLoadAgence() {
        loadAgence(basicAgenceRepository, "Basic Repository");
    }

    @Test
    public void fullLoadAgence() {
        loadAgence(fullAgenceRepository, "Full Repository");
    }

    // 3. Charger toutes les agences triées par ID décroissant
    @Test
    public void loadSortedAgences() {

        System.out.println("\n=== Agences triées par ID décroissant ===");

        Iterable<Agence> agences =
                basicAgenceRepository.findAll(
                        Sort.by(Sort.Direction.DESC, "idAgence")
                );

        for (Agence agence : agences) {
            System.out.println("ID Agence : " + agence.getIdAgence());
            System.out.println("Nom Agence : " + agence.getNom());
            System.out.println("Adresse : " + agence.getAdresse());
            System.out.println("Téléphone : " + agence.getTelephone());
            System.out.println("--------------------------------");
        }
    }

    // 4. Afficher les informations des agences sans leurs véhicules
    @Test
    public void loadAgencesSansVehicules() {

        System.out.println("\n=== Liste des agences ===");

        Iterable<Agence> agences = basicAgenceRepository.findAll();

        for (Agence agence : agences) {
            System.out.println("ID Agence : " + agence.getIdAgence());
            System.out.println("Nom Agence : " + agence.getNom());
            System.out.println("Adresse : " + agence.getAdresse());
            System.out.println("Téléphone : " + agence.getTelephone());
            System.out.println("--------------------------------");
        }
    }

    // 5. Pagination : afficher le nombre total de pages,
    // la page en cours et les agences de chaque page
    @Test
    public void loadPagedAgences() {

        System.out.println("\n=== Agences avec pagination ===");

        int pageSize = 2;
        int pageNumber = 0;

        Page<Agence> pageAgences;

        do {
            Pageable pageable = PageRequest.of(
                    pageNumber,
                    pageSize,
                    Sort.by(Sort.Direction.DESC, "idAgence")
            );

            pageAgences = basicAgenceRepository.findAll(pageable);

            System.out.println("\nNombre total de pages : "
                    + pageAgences.getTotalPages());

            System.out.println("Page en cours : "
                    + (pageAgences.getNumber() + 1));

            System.out.println("Nombre total d'agences : "
                    + pageAgences.getTotalElements());

            System.out.println("\n--- Agences de cette page ---");

            for (Agence agence : pageAgences.getContent()) {
                System.out.println("ID Agence : " + agence.getIdAgence());
                System.out.println("Nom Agence : " + agence.getNom());
                System.out.println("Adresse : " + agence.getAdresse());
                System.out.println("Téléphone : " + agence.getTelephone());
                System.out.println("--------------------------------");
            }

            pageNumber++;

        } while (pageAgences.hasNext());
    }
}
