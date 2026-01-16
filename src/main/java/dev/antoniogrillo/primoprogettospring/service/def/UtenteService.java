package dev.antoniogrillo.primoprogettospring.service.def;

import dev.antoniogrillo.primoprogettospring.entity.Utente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public interface UtenteService {
    Utente login(@NotNull(message = "devi inserire una email") String email,@NotEmpty(message = "devi inserire una password") String password);

    Utente creaUtente(@Valid Utente utente);

    Utente getUtenteById(Long id);
}
