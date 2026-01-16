package dev.antoniogrillo.primoprogettospring.facade.def;

import dev.antoniogrillo.primoprogettospring.dto.graphql.RegistraUtenteDTO;
import dev.antoniogrillo.primoprogettospring.entity.Automobile;
import dev.antoniogrillo.primoprogettospring.entity.Utente;

import java.util.List;
import java.util.Optional;

public interface GraphFacade {
    Utente getUtenteById(Long id);

    Optional<Automobile> getAutomobileById(Long id);

    Utente getProprietario(Automobile a);

    List<Automobile> getAutomobili(Utente u);

    long registraUtente(RegistraUtenteDTO utente);

    List<Automobile> findAutomobileByIDProprietario(List<Long> ids);
}
