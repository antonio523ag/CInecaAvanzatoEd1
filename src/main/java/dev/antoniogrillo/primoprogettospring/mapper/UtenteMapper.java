package dev.antoniogrillo.primoprogettospring.mapper;

import dev.antoniogrillo.primoprogettospring.dto.response.UtenteDTO;
import dev.antoniogrillo.primoprogettospring.entity.Utente;
import org.springframework.stereotype.Component;

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
}
