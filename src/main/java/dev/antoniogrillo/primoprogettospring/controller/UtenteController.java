package dev.antoniogrillo.primoprogettospring.controller;

import dev.antoniogrillo.primoprogettospring.dto.internal.LoginResponse;
import dev.antoniogrillo.primoprogettospring.dto.request.LoginRequestDTO;
import dev.antoniogrillo.primoprogettospring.dto.request.RegistraUtenteDTO;
import dev.antoniogrillo.primoprogettospring.dto.response.UtenteDTO;
import dev.antoniogrillo.primoprogettospring.entity.Utente;
import dev.antoniogrillo.primoprogettospring.facade.def.UtenteFacade;
import dev.antoniogrillo.primoprogettospring.service.def.UtenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UtenteController {

    private final UtenteFacade facade;

    @PostMapping("/all/utente/login")
    public ResponseEntity<UtenteDTO> login(@RequestBody LoginRequestDTO request){
//        UtenteDTO u=service.login(request);
//        if(u==null)return ResponseEntity.badRequest().build();
//        return ResponseEntity.ok(u);
        LoginResponse l=facade.login(request);
        return ResponseEntity.status(HttpStatus.OK).header("Authorization",l.getToken()).body(l.getUtente());
    }

    @PostMapping("/utente/whoAmI")
    public ResponseEntity<Utente> whoAmI(@AuthenticationPrincipal Utente utente){
        return ResponseEntity.ok(utente);
    }

    @PostMapping("/all/utente")
    public ResponseEntity<UtenteDTO> creaUtente(@RequestBody RegistraUtenteDTO utente){
        UtenteDTO u=facade.creaUtente(utente);
        return ResponseEntity.ok(u);
    }

    @PostMapping("/authorized/addAutomobile/{idAutomobile}")
    public ResponseEntity<Void> aggiungiAutomobile(@PathVariable long idAutomobile, @AuthenticationPrincipal Utente utente){
        facade.aggiungiAutomobile(idAutomobile,utente);
        return ResponseEntity.ok().build();
    }

}
