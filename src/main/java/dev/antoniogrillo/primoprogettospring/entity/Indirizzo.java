package dev.antoniogrillo.primoprogettospring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String via;
    private String civico;
    private String cap;
    private String citta;

    @ManyToMany
    @JoinTable(name = "indirizzi_dell_utente",
                        joinColumns = @JoinColumn(name = "indirizzo_fk"),
                        inverseJoinColumns = @JoinColumn(name = "utente_fk"),
                    uniqueConstraints = @UniqueConstraint(name = "indirizzo_utente_unici", columnNames = {"indirizzo_fk", "utente_fk"})
    )
    private List<Utente> utenti;

}
