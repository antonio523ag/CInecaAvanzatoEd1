package dev.antoniogrillo.primoprogettospring.facade.impl;

import dev.antoniogrillo.primoprogettospring.dto.graphql.RegistraUtenteDTO;
import dev.antoniogrillo.primoprogettospring.entity.Automobile;
import dev.antoniogrillo.primoprogettospring.entity.Ruolo;
import dev.antoniogrillo.primoprogettospring.entity.Utente;
import dev.antoniogrillo.primoprogettospring.facade.def.GraphFacade;
import dev.antoniogrillo.primoprogettospring.mapper.UtenteMapper;
import dev.antoniogrillo.primoprogettospring.service.def.AutomobileService;
import dev.antoniogrillo.primoprogettospring.service.def.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GraphFacadeImpl implements GraphFacade {
    private final UtenteService utenteService;
    private final AutomobileService automobileService;
    private final UtenteMapper utenteMapper;

    @Override
    public Utente getUtenteById(Long id) {
        return utenteService.getUtenteById(id);
    }

    @Override
    public Optional<Automobile> getAutomobileById(Long id) {
        return automobileService.getAutomobileById(id);
    }

    @Override
    public Utente getProprietario(Automobile a) {
        return automobileService.getAutomobileById(a.getId()).get().getProprietario();
    }

    @Override
    public List<Automobile> getAutomobili(Utente u) {
        return utenteService.getUtenteById(u.getId()).getAutomobiliPossedute();
    }

    @Override
    public long registraUtente(RegistraUtenteDTO utente) {
        Utente u=utenteMapper.toUtente(utente);
        u.setRuolo(Ruolo.UTENTE);
        return utenteService.creaUtente(u).getId();
    }

    @Override
    public List<Automobile> findAutomobileByIDProprietario(List<Long> ids) {
        return automobileService.findAutomobileByIDProprietario(ids);
    }
}
