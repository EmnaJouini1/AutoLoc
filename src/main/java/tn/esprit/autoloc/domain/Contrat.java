package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "contrat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    private LocalDate dateSignature;

    @Column(precision = 10, scale = 2)
    private BigDecimal montantTotal;

    private boolean valide;

    // Un contrat peut avoir plusieurs paiements
    // Le chargement du contrat implique le chargement des paiements
    @OneToMany(
            mappedBy = "contrat",
            fetch = FetchType.EAGER
    )
    private List<Paiement> paiements;
}