package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    // Une réservation concerne un seul véhicule
    @ManyToOne
    private Vehicule vehicule;

    // Une réservation appartient à un seul client
    @ManyToOne
    private Client client;

    // Une réservation peut être associée à un contrat
    @ManyToOne
    private Contrat contrat;
}