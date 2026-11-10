
package tn.esprit.autoloc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "vehicule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;

    @Column(nullable = false, unique = true, length = 20)
    private String immatriculation;

    @Column(nullable = false, length = 50)
    private String marque;

    @Column(nullable = false, length = 50)
    private String modele;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CategorieVehicule categorie;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal tarifJournalier;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatutVehicule statut;

    // Un véhicule peut avoir plusieurs réservations
    @OneToMany(
            mappedBy = "vehicule",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Reservation> reservations;

    // Un véhicule appartient à une seule agence
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_agence")
    private Agence agence;

    // Un véhicule peut avoir plusieurs maintenances
    @OneToMany(
            mappedBy = "vehicule",
            fetch = FetchType.LAZY
    )
    private List<Maintenance> maintenances;

    // Un véhicule peut avoir plusieurs équipements
    @ManyToMany
    private List<Equipement> equipements;
}
