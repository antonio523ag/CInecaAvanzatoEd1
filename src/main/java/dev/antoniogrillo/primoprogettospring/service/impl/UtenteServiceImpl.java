package dev.antoniogrillo.primoprogettospring.service.impl;

import dev.antoniogrillo.primoprogettospring.dto.internal.LoginResponse;
import dev.antoniogrillo.primoprogettospring.dto.request.LoginRequestDTO;
import dev.antoniogrillo.primoprogettospring.dto.request.RegistraUtenteDTO;
import dev.antoniogrillo.primoprogettospring.dto.response.UtenteDTO;
import dev.antoniogrillo.primoprogettospring.entity.Ruolo;
import dev.antoniogrillo.primoprogettospring.entity.Utente;
import dev.antoniogrillo.primoprogettospring.mapper.UtenteMapper;
import dev.antoniogrillo.primoprogettospring.repository.UtenteRepository;
import dev.antoniogrillo.primoprogettospring.service.def.GestoreTokenService;
import dev.antoniogrillo.primoprogettospring.service.def.UtenteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Validated
public class UtenteServiceImpl implements UtenteService {


    private final UtenteRepository repo;

    @Override
    public Utente login(@NotNull(message = "devi inserire una email") String email,
                        @NotEmpty(message = "devi inserire una password") String password) {
        return repo.findByEmailAndPassword(email,password)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "nessun utente con queste credenziali"));
    }

    @Override
    public Utente creaUtente(@Valid Utente utente) {
        return repo.save(utente);
    }

    @Override
    public Utente getUtenteById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato"));
    }

//
//    @Override
//    public LoginResponse login(LoginRequestDTO request) {
////        Optional<Utente> op= repo.findByEmailAndPassword(request.getUsername(), request.getPassword());
////        Utente u=null;
////        if(op.isPresent())u= op.get();
////        if(u!=null)return mapper.ToUtenteDTO(u);
////        else return null;
//
////        return repo.findByEmailAndPassword(request.getUsername(), request.getPassword())
////                .map(mapper::ToUtenteDTO)
////                .orElseThrow(()->new UtenteNonTrovatoException("nessun utente con queste credenziali"));
//
//        Utente u= repo.findByEmailAndPassword(request.getUsername(), request.getPassword())
//                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "nessun utente con queste credenziali"));
//        LoginResponse l=new LoginResponse();
//        l.setUtente(mapper.ToUtenteDTO(u));
//        l.setToken(tokenService.generateToken(u));
//        return l;
//    }
//
//    @Override
//    public UtenteDTO creaUtente(RegistraUtenteDTO utente) {
//        Utente u=mapper.toUtente(utente);
//        u.setRuolo(Ruolo.UTENTE);
//        u=repo.save(u);
//        return mapper.ToUtenteDTO(u);
//    }


}
