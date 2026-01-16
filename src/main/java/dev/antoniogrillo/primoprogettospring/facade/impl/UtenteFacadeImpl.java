package dev.antoniogrillo.primoprogettospring.facade.impl;

import dev.antoniogrillo.primoprogettospring.dto.internal.LoginResponse;
import dev.antoniogrillo.primoprogettospring.dto.request.LoginRequestDTO;
import dev.antoniogrillo.primoprogettospring.dto.request.RegistraUtenteDTO;
import dev.antoniogrillo.primoprogettospring.dto.response.UtenteDTO;
import dev.antoniogrillo.primoprogettospring.entity.Automobile;
import dev.antoniogrillo.primoprogettospring.entity.Ruolo;
import dev.antoniogrillo.primoprogettospring.entity.Utente;
import dev.antoniogrillo.primoprogettospring.facade.def.UtenteFacade;
import dev.antoniogrillo.primoprogettospring.mapper.UtenteMapper;
import dev.antoniogrillo.primoprogettospring.service.def.AutomobileService;
import dev.antoniogrillo.primoprogettospring.service.def.GestoreTokenService;
import dev.antoniogrillo.primoprogettospring.service.def.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtenteFacadeImpl implements UtenteFacade {

    private final UtenteService utenteService;
    private final UtenteMapper utenteMapper;
    private final GestoreTokenService gestoreTokenService;
    private final AutomobileService automobileService;

    @Override
    public LoginResponse login(LoginRequestDTO request) {
        Utente u=utenteService.login(request.getUsername(),request.getPassword());
        String token=gestoreTokenService.generateToken(u);
        LoginResponse l=new LoginResponse();
        l.setUtente(utenteMapper.ToUtenteDTO(u));
        l.setToken(token);
        return l;
    }

    @Override
    public UtenteDTO creaUtente(RegistraUtenteDTO utente) {
        Utente u=utenteMapper.toUtente(utente);
        u.setRuolo(Ruolo.UTENTE);
        u=utenteService.creaUtente(u);
        return utenteMapper.ToUtenteDTO(u);
    }

    @Override
    public void aggiungiAutomobile(long idAutomobile, Utente utente) {
        Automobile a=automobileService.getAutomobileById(idAutomobile).get();
        a.setProprietario(utente);
        automobileService.updateAutomobile(a);
    }

    @Override
    public Utente getUtenteById(Long id) {
        return utenteService.getUtenteById(id);
    }

    @Override
    public Automobile getAutomobileById(Long id) {
        return automobileService.getAutomobileById(id).get();
    }
}
