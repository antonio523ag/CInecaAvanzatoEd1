package dev.antoniogrillo.primoprogettospring.controller;

import dev.antoniogrillo.primoprogettospring.entity.Automobile;
import dev.antoniogrillo.primoprogettospring.entity.Utente;
import dev.antoniogrillo.primoprogettospring.service.def.AutomobileService;
import dev.antoniogrillo.primoprogettospring.service.def.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AutomobileController {

    private final AutomobileService automobileService;
    private final UtenteService utenteService;

    @PostMapping("/authorized/automobile")
    public ResponseEntity<Void> aggiungiAutomobile(@RequestBody Automobile a, @AuthenticationPrincipal Utente utente){
        utente=utenteService.getUtenteById(utente.getId());
        a.setProprietario(utente);
        automobileService.salva(a);
        return ResponseEntity.ok().build();
    }

}
