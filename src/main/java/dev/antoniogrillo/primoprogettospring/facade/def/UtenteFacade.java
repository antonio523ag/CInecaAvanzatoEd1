package dev.antoniogrillo.primoprogettospring.facade.def;

import dev.antoniogrillo.primoprogettospring.dto.internal.LoginResponse;
import dev.antoniogrillo.primoprogettospring.dto.request.LoginRequestDTO;
import dev.antoniogrillo.primoprogettospring.dto.request.RegistraUtenteDTO;
import dev.antoniogrillo.primoprogettospring.dto.response.UtenteDTO;
import dev.antoniogrillo.primoprogettospring.entity.Automobile;
import dev.antoniogrillo.primoprogettospring.entity.Utente;
import jakarta.validation.Valid;

public interface UtenteFacade {
    LoginResponse login(LoginRequestDTO request);

    UtenteDTO creaUtente(@Valid RegistraUtenteDTO utente);

    void aggiungiAutomobile(long idAutomobile, Utente utente);

    Utente getUtenteById(Long id);

    Automobile getAutomobileById(Long id);
}
