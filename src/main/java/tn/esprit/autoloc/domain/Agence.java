package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "agence")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(length = 255)
    private String adresse;

    @Column(length = 20)
    private String telephone;

    // Une agence possède plusieurs véhicules
    @OneToMany(
            mappedBy = "agence",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<Vehicule> vehicules;

    // Le chargement d'une agence ne charge pas les employés
    // La suppression d'une agence ne supprime pas les employés
    @OneToMany(
            mappedBy = "agence",
            fetch = FetchType.LAZY
    )
    private List<Employe> employes;
}