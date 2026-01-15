package dev.antoniogrillo.primoprogettospring.service.impl;

import dev.antoniogrillo.primoprogettospring.dto.internal.LoginResponse;
import dev.antoniogrillo.primoprogettospring.dto.request.LoginRequestDTO;
import dev.antoniogrillo.primoprogettospring.dto.response.UtenteDTO;
import dev.antoniogrillo.primoprogettospring.entity.Utente;
import dev.antoniogrillo.primoprogettospring.mapper.UtenteMapper;
import dev.antoniogrillo.primoprogettospring.repository.UtenteRepository;
import dev.antoniogrillo.primoprogettospring.service.def.GestoreTokenService;
import dev.antoniogrillo.primoprogettospring.service.def.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UtenteServiceImpl implements UtenteService {


    private final UtenteRepository repo;
    private final GestoreTokenService tokenService;
    private final UtenteMapper mapper;


    @Override
    public LoginResponse login(LoginRequestDTO request) {
//        Optional<Utente> op= repo.findByEmailAndPassword(request.getUsername(), request.getPassword());
//        Utente u=null;
//        if(op.isPresent())u= op.get();
//        if(u!=null)return mapper.ToUtenteDTO(u);
//        else return null;

//        return repo.findByEmailAndPassword(request.getUsername(), request.getPassword())
//                .map(mapper::ToUtenteDTO)
//                .orElseThrow(()->new UtenteNonTrovatoException("nessun utente con queste credenziali"));

        Utente u= repo.findByEmailAndPassword(request.getUsername(), request.getPassword())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "nessun utente con queste credenziali"));
        LoginResponse l=new LoginResponse();
        l.setUtente(mapper.ToUtenteDTO(u));
        l.setToken(tokenService.generateToken(u));
        return l;
    }
}
