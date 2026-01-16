package dev.antoniogrillo.primoprogettospring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Utente implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Il nome non può essere vuoto")
    @Column(nullable = false)
    private String nome;
    @NotBlank(message = "il cognome non puo essere vuto")
    private String cognome;
    @NotNull(message = "L'email non può essere vuota")
    @Email(message = "Devi inserire un indirizzo email valido")
    private String email;
    @NotBlank(message = "La password non può essere vuota")
    private String password;
    @Past(message = "Non puoi essere nato nel futuro")
    private LocalDate dataNascita;
    @NotNull(message = "Il ruolo non può essere vuoto")
    private Ruolo ruolo;
    @Transient
    private int anni;

    @OneToMany(mappedBy = "proprietario")
    private List<Automobile> automobiliPossedute;

    @ManyToMany(mappedBy = "utenti")
    private Set<Indirizzo> indirizzi;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+ruolo.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }
}
