package dev.antoniogrillo.primoprogettospring.service.def;


import dev.antoniogrillo.primoprogettospring.entity.Utente;

public interface GestoreTokenService {

    Utente getUtenteByToken(String token);
    String generateToken(Utente utente);
}
