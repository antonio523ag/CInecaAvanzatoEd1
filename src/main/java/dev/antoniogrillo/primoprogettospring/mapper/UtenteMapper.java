package dev.antoniogrillo.primoprogettospring.mapper;

import dev.antoniogrillo.primoprogettospring.dto.request.RegistraUtenteDTO;
import dev.antoniogrillo.primoprogettospring.dto.response.UtenteDTO;
import dev.antoniogrillo.primoprogettospring.entity.Utente;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class UtenteMapper {

    public UtenteDTO ToUtenteDTO(Utente u){
        UtenteDTO dto = new UtenteDTO();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setCognome(u.getCognome());
        dto.setEmail(u.getEmail());
        int anni=(int) ChronoUnit.YEARS.between(u.getDataNascita(), LocalDate.now());
        dto.setAnni(anni);
        return dto;
    }

    public Utente toUtente(RegistraUtenteDTO utente) {
        Utente u=new Utente();
        u.setNome(utente.nome());
        u.setCognome(utente.cognome());
        u.setEmail(utente.email());
        u.setPassword(utente.password());
        u.setDataNascita(utente.dataNascita());
        return u;
    }

    public Utente toUtente(dev.antoniogrillo.primoprogettospring.dto.graphql.RegistraUtenteDTO utente) {
        Utente u=new Utente();
        u.setNome(utente.nome());
        u.setCognome(utente.cognome());
        u.setEmail(utente.email());
        u.setPassword(utente.password());
        try {

            u.setDataNascita(LocalDate.parse(utente.dataNascita()));
        }catch (DateTimeException ignored){}
        return u;
    }
}
